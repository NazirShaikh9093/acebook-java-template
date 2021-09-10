CREATE TABLE likes (
  id bigserial PRIMARY KEY,
  userID bigserial,
  postID bigserial,
  counter bigserial,
  constraint fk_userid foreign key(userID) references users(id),
  constraint fk_postid foreign key(postID) references posts(id)
);