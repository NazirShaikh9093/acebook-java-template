package com.makersacademy.acebook.model;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LikeFunctions {

    @Autowired
    UserRepository userRepository;

    //    @Transactions
//    @Override
//    public int addPostLike(long user_id, long post_id) {
//        int incrementValue = 1;
//        Post post = PostRepository.findByPostID(post_id)
//        Optional<long> likeID = LikeRepository.FindPostLikedIDByUserID(user_id, post_id);
//        Like like = new Like(user_id, post_id, ContentType.POST);
//        likeRepository.save(like);
//    };

    public long getUserID(String username) {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername() == username) {
                return user.getIDnumber();
            }
        }
        return 404;
    }
}
