CREATE TABLE likes (
  id bigserial PRIMARY KEY,
  userID int(255),
  postID int(255),
  counter int(255) NOT NULL,
  constraint fk_userid foreign key(userID) references users(id),
  constraint fk_postid foreign key(postID) references posts(id)
);