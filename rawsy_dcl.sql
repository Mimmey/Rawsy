INSERT INTO country (_name) VALUES ('Russia');
INSERT INTO country (_name) VALUES ('Estonia');
INSERT INTO country (_name) VALUES ('Finland');
INSERT INTO _user (nickname, email, password_hash, country_id, about) VALUES ('user1', 'user1', 'password1', 1, 'I am user 1');
INSERT INTO _user (nickname, email, password_hash, country_id, about) VALUES ('user2', 'user2', 'password2', 1, 'I am user 2');
INSERT INTO _user (nickname, email, password_hash, country_id, about) VALUES ('user3', 'user3', 'password3', 1, 'I am user 3');
INSERT INTO track_type (_name) VALUES ('Full');
INSERT INTO track_type (_name) VALUES ('Solo');
INSERT INTO track_type (_name) VALUES ('Raw');
INSERT INTO track_genre (_name) VALUES ('Country');
INSERT INTO track_genre (_name) VALUES ('Soul');
INSERT INTO track_genre (_name) VALUES ('Rock');
INSERT INTO track_mood (_name) VALUES ('Sunny');
INSERT INTO track_mood (_name) VALUES ('Angry');
INSERT INTO track_mood (_name) VALUES ('Sad');
INSERT INTO track (_name, author_id, type_id, has_vocal, is_cycled, bpm, duration, audio_preview, cost, track_archive_path)
            VALUES ('Sunny gunny', 1, 1, FALSE, FALSE, 120, 120, 'preview1 path', 100, 'track1 path');
INSERT INTO track (_name, author_id, type_id, has_vocal, is_cycled, bpm, duration, audio_preview, cost, track_archive_path)
            VALUES ('Angry gunny', 1, 2, TRUE, TRUE, 80, 5, 'preview2 path', 150, 'track2 path');
INSERT INTO track (_name, author_id, type_id, has_vocal, is_cycled, bpm, duration, audio_preview, cost, track_archive_path)
            VALUES ('Sad gunny', 1, 3, TRUE, TRUE, 150, 20, 'preview3 path', 200, 'track3 path');
INSERT INTO genre_list (track_id, genre_id) VALUES (1, 1);
INSERT INTO genre_list (track_id, genre_id) VALUES (2, 1);
INSERT INTO genre_list (track_id, genre_id) VALUES (3, 1);
INSERT INTO mood_list (track_id, mood_id) VALUES (1, 1);
INSERT INTO mood_list (track_id, mood_id) VALUES (2, 2);
INSERT INTO mood_list (track_id, mood_id) VALUES (3, 3);
INSERT INTO favourites_list (owner_id, track_id) VALUES (1, 3);
INSERT INTO favourites_list (owner_id, track_id) VALUES (2, 3);
INSERT INTO purchase_list (purchaser_id, track_id) VALUES (1, 3);
INSERT INTO purchase_list (purchaser_id, track_id) VALUES (2, 3);
INSERT INTO comment (comment_author_id, track_id, content, rate)
            VALUES (1, 2, 'comment for track2', 5);
INSERT INTO subscription_list (subscriber_id, subject_id) VALUES (1, 2);
INSERT INTO subscription_list (subscriber_id, subject_id) VALUES (1, 3);
INSERT INTO subscription_list (subscriber_id, subject_id) VALUES (1, 1);





