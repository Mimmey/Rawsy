/*to start \c rawsy*/

CREATE TABLE IF NOT EXISTS country (id SERIAL PRIMARY KEY,
                                    _name VARCHAR(50) UNIQUE NOT NULL);

CREATE TABLE IF NOT EXISTS _user (id SERIAL PRIMARY KEY,
                                  nickname VARCHAR(30) UNIQUE NOT NULL,
                                  email VARCHAR(100) UNIQUE NOT NULL,
                                  password_hash VARCHAR(100) NOT NULL,
                                  is_banned BOOLEAN DEFAULT FALSE,
                                  country_id INTEGER NOT NULL,
                                  about VARCHAR(500) NOT NULL,
                                  jingle_path VARCHAR(500),
                                  avatar_path VARCHAR(500),
                                  subscribers_count BIGINT DEFAULT 0 CHECK (subscribers_count >= 0),
                                  subscriptions_count BIGINT DEFAULT 0 CHECK (subscriptions_count >= 0),
                                  tracks_in_other_users_favourites_count BIGINT DEFAULT 0 CHECK (tracks_in_other_users_favourites_count >= 0),
                                  published_tracks_count BIGINT DEFAULT 0 CHECK (published_tracks_count >= 0),
                                  tracks_purchased_by_other_users_count BIGINT DEFAULT 0 CHECK (tracks_purchased_by_other_users_count >= 0),
                                  FOREIGN KEY(country_id) REFERENCES country (id) ON DELETE RESTRICT ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS media_link (id BIGSERIAL PRIMARY KEY,
                                   owner_id BIGINT NOT NULL,
                                   content VARCHAR(50) NOT NULL,
                                   FOREIGN KEY(owner_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE);


CREATE TABLE IF NOT EXISTS track_type (id SERIAL PRIMARY KEY,
                                       _name VARCHAR(30) UNIQUE NOT NULL);

CREATE TABLE IF NOT EXISTS track_genre (id SERIAL PRIMARY KEY,
                                        _name VARCHAR(30) UNIQUE NOT NULL);

CREATE TABLE IF NOT EXISTS track_mood (id SERIAL PRIMARY KEY,
                                       _name VARCHAR(30) UNIQUE NOT NULL);

CREATE TABLE IF NOT EXISTS track (id BIGSERIAL PRIMARY KEY,
                                  _name VARCHAR(40) UNIQUE NOT NULL,
                                  publishing_timestamp TIMESTAMP NOT NULL DEFAULT NOW(),
                                  author_id BIGINT NOT NULL,
                                  type_id INTEGER NOT NULL,
                                  rating REAL DEFAULT 0 CHECK (rating >= 0 AND rating <= 5),
                                  about VARCHAR(500) NOT NULL,
                                  invoice VARCHAR(500) NOT NULL,
                                  has_vocal BOOLEAN NOT NULL,
                                  is_cycled BOOLEAN NOT NULL,
                                  bpm INTEGER NOT NULL CHECK (bpm >= 0),
                                  duration INTEGER NOT NULL CHECK (duration >= 0),
                                  cost BIGINT DEFAULT 0 CHECK (cost >= 0),
                                  audio_preview_path VARCHAR(500) NOT NULL,
                                  track_archive_path VARCHAR(500) NOT NULL,
                                  comments_count BIGINT DEFAULT 0 CHECK (comments_count >= 0),
                                  purchases_count BIGINT DEFAULT 0 CHECK (purchases_count >= 0),
                                  in_favourites_count BIGINT DEFAULT 0 CHECK (in_favourites_count >= 0),
                                  FOREIGN KEY(author_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE,
                                  FOREIGN KEY(type_id) REFERENCES track_type (id) ON DELETE RESTRICT ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS genre_list (track_id BIGINT NOT NULL,
                                          genre_id INTEGER NOT NULL,
                                          PRIMARY KEY(track_id, genre_id),
                                          FOREIGN KEY(track_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE,
                                          FOREIGN KEY(genre_id) REFERENCES track_genre (id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS mood_list (track_id BIGINT NOT NULL,
                                       mood_id INTEGER NOT NULL,
                                       PRIMARY KEY(track_id, mood_id),
                                       FOREIGN KEY(track_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE,
                                       FOREIGN KEY(mood_id) REFERENCES track_mood (id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS purchase_list (purchaser_id BIGINT NOT NULL,
                                          track_id BIGINT NOT NULL,
                                          _timestamp TIMESTAMP NOT NULL DEFAULT NULL,
                                          PRIMARY KEY(purchaser_id, track_id),
                                          FOREIGN KEY(purchaser_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE,
                                          FOREIGN KEY(track_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS favourites_list (owner_id BIGINT NOT NULL,
                                            track_id BIGINT NOT NULL,
                                            _timestamp TIMESTAMP NOT NULL DEFAULT NULL,
                                            PRIMARY KEY(owner_id, track_id),
                                            FOREIGN KEY(owner_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE,
                                            FOREIGN KEY(track_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS tracks_in_basket_list (owner_id BIGINT NOT NULL,
                                                  track_id BIGINT NOT NULL,
                                                  PRIMARY KEY(owner_id, track_id),
                                                  FOREIGN KEY(owner_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE,
                                                  FOREIGN KEY(track_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS subscription_list (subscriber_id BIGINT NOT NULL,
                                              subject_id BIGINT NOT NULL,
                                              CHECK(subscriber_id != subject_id),
                                              PRIMARY KEY(subscriber_id, subject_id),
                                              FOREIGN KEY(subscriber_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE,
                                              FOREIGN KEY(subject_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS comment (comment_author_id BIGINT DEFAULT 0,
                                    track_id BIGINT NOT NULL,
                                    content VARCHAR(100) NOT NULL,
                                    rate SMALLINT NOT NULL CHECK (rate >= 1 AND rate <= 5),
                                    _timestamp TIMESTAMP DEFAULT NOW(),
                                    PRIMARY KEY(comment_author_id, track_id),
                                    FOREIGN KEY(comment_author_id) REFERENCES _user (id) ON DELETE SET DEFAULT ON UPDATE CASCADE,
                                    FOREIGN KEY(track_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS report (id BIGSERIAL PRIMARY KEY,
                                   author_id BIGINT NOT NULL,
                                   content VARCHAR(1000) NOT NULL,
                                   _timestamp TIMESTAMP DEFAULT NOW(),
                                   FOREIGN KEY(author_id) REFERENCES _user (id) ON DELETE SET DEFAULT ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS user_report (report_id BIGINT NOT NULL,
                                        user_subject_id BIGINT NOT NULL,
                                        PRIMARY KEY(report_id, user_subject_id),
                                        FOREIGN KEY(report_id) REFERENCES report (id) ON DELETE CASCADE ON UPDATE CASCADE,
                                        FOREIGN KEY(user_subject_id) REFERENCES _user (id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS track_report (report_id BIGINT NOT NULL,
                                         track_subject_id BIGINT NOT NULL,
                                         PRIMARY KEY(report_id, track_subject_id),
                                         FOREIGN KEY(report_id) REFERENCES report (id) ON DELETE CASCADE ON UPDATE CASCADE,
                                         FOREIGN KEY(track_subject_id) REFERENCES track (id) ON DELETE CASCADE ON UPDATE CASCADE);


/*Checks
Когда автор удален, его треки сносятся, а за них уже заплатили
author_id = 0 - deleted
 */


CREATE OR REPLACE FUNCTION increment_subscribers_count() RETURNS TRIGGER
    AS $$
        BEGIN
            UPDATE _user SET subscribers_count=subscribers_count + 1 WHERE id=NEW.subject_id;
            return NEW;
        END;
    $$ LANGUAGE plpgsql;

CREATE TRIGGER tr_increment_subscribers_count AFTER INSERT ON subscription_list
    FOR EACH ROW EXECUTE PROCEDURE increment_subscribers_count();


CREATE OR REPLACE FUNCTION increment_subscriptions_count() RETURNS TRIGGER
    AS $$
        BEGIN
            UPDATE _user SET subscriptions_count=subscriptions_count + 1 WHERE id=NEW.subscriber_id;
            return NEW;
        END;
    $$ LANGUAGE plpgsql;

CREATE TRIGGER tr_increment_subscriptions_count AFTER INSERT ON subscription_list
    FOR EACH ROW EXECUTE PROCEDURE increment_subscriptions_count();


CREATE OR REPLACE FUNCTION increment_tracks_in_other_users_favourites_count() RETURNS TRIGGER
    AS $$
        DECLARE
            track_author_id INTEGER;
        BEGIN
            SELECT author_id INTO track_author_id FROM track WHERE id = NEW.track_id;
            UPDATE _user SET tracks_in_other_users_favourites_count=tracks_in_other_users_favourites_count + 1 WHERE id=track_author_id;
            return NEW;
        END;
    $$ LANGUAGE plpgsql;

CREATE TRIGGER tr_increment_tracks_in_other_users_favourites_count AFTER INSERT ON favourites_list
    FOR EACH ROW EXECUTE PROCEDURE increment_tracks_in_other_users_favourites_count();


CREATE OR REPLACE FUNCTION increment_published_tracks_count() RETURNS TRIGGER
    AS $$
        BEGIN
            UPDATE _user SET published_tracks_count=published_tracks_count + 1 WHERE id=NEW.author_id;
            return NEW;
        END;
    $$ LANGUAGE plpgsql;

CREATE TRIGGER tr_increment_published_tracks_count AFTER INSERT ON track
    FOR EACH ROW EXECUTE PROCEDURE increment_published_tracks_count();


CREATE OR REPLACE FUNCTION increment_tracks_purchased_by_other_users_count() RETURNS TRIGGER
    AS $$
        DECLARE
            track_author_id INTEGER;
        BEGIN
            SELECT author_id INTO track_author_id FROM track WHERE id = NEW.track_id;
            UPDATE _user SET tracks_purchased_by_other_users_count=tracks_purchased_by_other_users_count + 1 WHERE _user.id=track_author_id;
            return NEW;
        END;
    $$ LANGUAGE plpgsql;

CREATE TRIGGER tr_increment_tracks_purchased_by_other_users_count AFTER INSERT ON purchase_list
    FOR EACH ROW EXECUTE PROCEDURE increment_tracks_purchased_by_other_users_count();


CREATE OR REPLACE FUNCTION increment_comments_count() RETURNS TRIGGER
    AS $$
        BEGIN
        UPDATE track SET comments_count=comments_count + 1 WHERE id=NEW.track_id;
        return NEW;
    END;
    $$ LANGUAGE plpgsql;

CREATE TRIGGER tr_increment_comments_count AFTER INSERT ON comment
    FOR EACH ROW EXECUTE PROCEDURE increment_comments_count();


CREATE OR REPLACE FUNCTION increment_comments_count() RETURNS TRIGGER
    AS $$
        BEGIN
            UPDATE track SET comments_count=comments_count + 1 WHERE id=NEW.track_id;
            return NEW;
        END;
    $$ LANGUAGE plpgsql;

CREATE TRIGGER tr_increment_comments_count AFTER INSERT ON comment
    FOR EACH ROW EXECUTE PROCEDURE increment_comments_count();


CREATE OR REPLACE FUNCTION increment_purchases_count() RETURNS TRIGGER
    AS $$
        BEGIN
            UPDATE track SET purchases_count=purchases_count + 1 WHERE id=NEW.track_id;
            return NEW;
        END;
    $$ LANGUAGE plpgsql;

CREATE TRIGGER tr_increment_purchases_count AFTER INSERT ON favourites_list
    FOR EACH ROW EXECUTE PROCEDURE increment_purchases_count();


CREATE OR REPLACE FUNCTION increment_in_favourites_count() RETURNS TRIGGER
    AS $$
        BEGIN
            UPDATE track SET in_favourites_count=in_favourites_count + 1 WHERE id=NEW.track_id;
            return NEW;
        END;
    $$ LANGUAGE plpgsql;

CREATE TRIGGER tr_increment_in_favourites_count AFTER INSERT ON favourites_list
    FOR EACH ROW EXECUTE PROCEDURE increment_in_favourites_count();

/*TODO: rating func*/



