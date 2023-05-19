-- to start \c rawsy

CREATE TABLE IF NOT EXISTS country
(
    id    SERIAL PRIMARY KEY,
    _name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS _user
(
    id                                     SERIAL PRIMARY KEY,
    nickname                               VARCHAR(30) UNIQUE  NOT NULL,
    email                                  VARCHAR(100) UNIQUE NOT NULL,
    password                               VARCHAR(100)        NOT NULL,
    is_banned                              BOOLEAN             NOT NULL,
    role                                   VARCHAR(10)         NOT NULL,
    country_id                             INTEGER             NOT NULL,
    about                                  VARCHAR(500),
    jingle_path                            VARCHAR(500)        NOT NULL,
    avatar_path                            VARCHAR(500)        NOT NULL,
    tracks_in_other_users_favourites_count BIGINT              NOT NULL CHECK (tracks_in_other_users_favourites_count >= 0),
    tracks_purchased_by_other_users_count  BIGINT              NOT NULL CHECK (tracks_purchased_by_other_users_count >= 0),
    FOREIGN KEY (country_id) REFERENCES country (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS media_link
(
    id       BIGSERIAL,
    owner_id BIGINT      NOT NULL,
    content  VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS track_type
(
    id    SERIAL PRIMARY KEY,
    _name VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS track_genre
(
    id    SERIAL PRIMARY KEY,
    _name VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS track_mood
(
    id    SERIAL PRIMARY KEY,
    _name VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS track
(
    id                   BIGSERIAL PRIMARY KEY,
    name                 VARCHAR(40) UNIQUE NOT NULL,
    publishing_timestamp TIMESTAMP          NOT NULL,
    author_id            BIGINT             NOT NULL,
    type_id              INTEGER            NOT NULL,
    rating               REAL CHECK (rating >= 0 AND rating <= 5),
    about                VARCHAR(500)       NOT NULL,
    invoice              VARCHAR(500)       NOT NULL,
    has_vocal            BOOLEAN,
    is_cycled            BOOLEAN,
    bpm                  INTEGER            NOT NULL,
    duration             INTEGER            NOT NULL CHECK (duration >= 0),
    cost                 BIGINT,
    audio_preview_path   VARCHAR(500)/*      NOT NULL*/,
    track_archive_path   VARCHAR(500)/*       NOT NULL*/,
    FOREIGN KEY (author_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (type_id) REFERENCES track_type (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS genre_list
(
    track_id BIGINT  NOT NULL,
    genre_id INTEGER NOT NULL,
    PRIMARY KEY (track_id, genre_id),
    FOREIGN KEY (track_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES track_genre (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS mood_list
(
    track_id BIGINT  NOT NULL,
    mood_id  INTEGER NOT NULL,
    PRIMARY KEY (track_id, mood_id),
    FOREIGN KEY (track_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (mood_id) REFERENCES track_mood (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS purchase_list
(
    purchaser_id BIGINT    NOT NULL,
    track_id     BIGINT    NOT NULL,
    cost         BIGINT    NOT NULL,
    _timestamp   TIMESTAMP NOT NULL,
    PRIMARY KEY (purchaser_id, track_id),
    FOREIGN KEY (purchaser_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (track_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS favourites_list
(
    owner_id   BIGINT    NOT NULL,
    track_id   BIGINT    NOT NULL,
    _timestamp TIMESTAMP NOT NULL,
    PRIMARY KEY (owner_id, track_id),
    FOREIGN KEY (owner_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (track_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tracks_in_basket_list
(
    owner_id BIGINT NOT NULL,
    track_id BIGINT NOT NULL,
    PRIMARY KEY (owner_id, track_id),
    FOREIGN KEY (owner_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (track_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS subscription_list
(
    subscriber_id BIGINT NOT NULL,
    subject_id    BIGINT NOT NULL,
    CHECK (subscriber_id != subject_id),
    PRIMARY KEY (subscriber_id, subject_id),
    FOREIGN KEY (subscriber_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS comment
(
    author_id  BIGINT,
    track_id   BIGINT       NOT NULL,
    content    VARCHAR(100) NOT NULL,
    rate       SMALLINT     NOT NULL,
    _timestamp TIMESTAMP,
    PRIMARY KEY (author_id, track_id),
    FOREIGN KEY (author_id) REFERENCES _user (id) ON DELETE SET DEFAULT ON UPDATE CASCADE,
    FOREIGN KEY (track_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS report
(
    id         BIGSERIAL PRIMARY KEY,
    author_id  BIGINT        NOT NULL,
    content    VARCHAR(1000) NOT NULL,
    _timestamp TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES _user (id) ON DELETE SET DEFAULT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS user_report
(
    report_id       BIGINT NOT NULL,
    user_subject_id BIGINT NOT NULL,
    PRIMARY KEY (report_id, user_subject_id),
    FOREIGN KEY (report_id) REFERENCES report (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_subject_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS track_report
(
    report_id        BIGINT NOT NULL,
    track_subject_id BIGINT NOT NULL,
    PRIMARY KEY (report_id, track_subject_id),
    FOREIGN KEY (report_id) REFERENCES report (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (track_subject_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Когда автор удален, его треки сносятся, а за них уже заплатили

CREATE OR REPLACE FUNCTION increment_tracks_in_other_users_favourites_count() RETURNS TRIGGER
AS
$$
DECLARE
    track_author_id INTEGER;
BEGIN
    SELECT author_id INTO track_author_id FROM track WHERE id = NEW.track_id;
    UPDATE _user
    SET tracks_in_other_users_favourites_count=tracks_in_other_users_favourites_count + 1
    WHERE id = track_author_id;
    return NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tr_increment_tracks_in_other_users_favourites_count
    AFTER INSERT
    ON favourites_list
    FOR EACH ROW
EXECUTE PROCEDURE increment_tracks_in_other_users_favourites_count();

CREATE OR REPLACE FUNCTION increment_tracks_purchased_by_other_users_count() RETURNS TRIGGER
AS
$$
DECLARE
    track_author_id INTEGER;
BEGIN
    SELECT author_id INTO track_author_id FROM track WHERE id = NEW.track_id;
    UPDATE _user
    SET tracks_purchased_by_other_users_count=tracks_purchased_by_other_users_count + 1
    WHERE _user.id = track_author_id;
    return NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tr_increment_tracks_purchased_by_other_users_count
    AFTER INSERT
    ON purchase_list
    FOR EACH ROW
EXECUTE PROCEDURE increment_tracks_purchased_by_other_users_count();

CREATE INDEX user_nickname_idx_hash ON _user USING hash (nickname);

CREATE INDEX media_link_owner_id_idx_hash ON media_link USING hash (owner_id);

CREATE INDEX track_author_id_idx_hash ON track USING hash (author_id);
CREATE INDEX track_rating_idx_hash ON track USING hash (rating);
CREATE INDEX track_type_id_idx_hash ON track USING hash (type_id);
CREATE INDEX track_has_vocal_idx_hash ON track USING hash (has_vocal);
CREATE INDEX track_is_cycled_idx_hash ON track USING hash (is_cycled);
CREATE INDEX track_bpm_idx_hash ON track USING hash (bpm);
CREATE INDEX track_duration_idx_hash ON track USING hash (duration);

CREATE INDEX purchase_list_timestamp_idx_hash ON purchase_list USING hash (_timestamp);
CREATE INDEX purchase_list_purchaser_id_idx_hash ON purchase_list USING hash (purchaser_id);

CREATE INDEX favourites_list_owner_id_idx_hash ON favourites_list USING hash (owner_id);
CREATE INDEX tracks_in_basket_list_owner_id_idx_hash ON tracks_in_basket_list USING hash (owner_id);
CREATE INDEX subscription_list_subscriber_idx_hash ON subscription_list USING hash (subject_id);
CREATE INDEX subscription_list_subscription_id_idx_hash ON subscription_list USING hash (subscriber_id);

CREATE INDEX comment_track_id_idx_hash ON comment USING hash (track_id);
CREATE INDEX user_report_user_subject_id_idx_hash ON user_report USING hash (user_subject_id);
CREATE INDEX track_report_track_subject_id_idx_hash ON track_report USING hash (track_subject_id);

/*TODO: rating func*/



