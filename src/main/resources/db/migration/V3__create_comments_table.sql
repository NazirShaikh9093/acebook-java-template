DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
  id bigserial PRIMARY KEY,
  postid bigserial,
  content varchar(250) NOT NULL
);