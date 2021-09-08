DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
  id bigserial PRIMARY KEY,
  postid int,
  content varchar(250) NOT NULL,
  constraint fk_post_comments foreign key(postid) references posts(id)
);