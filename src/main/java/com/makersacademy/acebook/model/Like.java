package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;



public class Like {
    @Transactions
    @Override
    public int addPostLike(long user_id, long post_id) {
        int incrementValue = 1;
        Post post = PostRepository.findByPostID(post_id)
        Optional<long> likeID = LikeRepository.FindPostLikedIDByUserID(user_id, post_id);
        Like like = new Like(user_id, post_id, ContentType.POST);
        likeRepository.save(like);
    }
}
