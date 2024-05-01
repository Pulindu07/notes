CREATE SEQUENCE IF NOT EXISTS notes_id_seq;

create TABLE notes(
                      id int4 NOT NULL DEFAULT nextval('notes_id_seq'::regclass),
                      user_id VARCHAR(100),
                      title VARCHAR(200),
                      note VARCHAR,
                      status VARCHAR(50),
                      date_created TIMESTAMP,
                      PRIMARY KEY ("id")
);

CREATE SEQUENCE notes_seq START 1;