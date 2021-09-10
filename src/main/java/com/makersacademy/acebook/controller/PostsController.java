package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Like;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.model.LikeFunctions;
import com.makersacademy.acebook.repository.LikesRepository;
import com.makersacademy.acebook.repository.PostRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class PostsController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    LikesRepository likesRepository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        Collections.reverse((List<?>) posts);
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Post());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Post post) {
        postRepository.save(post);
        return new RedirectView("/posts");
    }

    @DeleteMapping("/posts/delete")
    public RedirectView delete(@RequestParam long id) {
        postRepository.deleteById(id);
        return new RedirectView("/posts");
    }

    @PostMapping("/post/like")
    public RedirectView like(@RequestParam long postid) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        LikeFunctions object = new LikeFunctions();
        long userID = object.getUserID(username);

        Like like = new Like(userID, postid, 1);
        likesRepository.save(like);

        return new RedirectView("/posts");
    }

    @GetMapping("/post/comments")
    public String comments(Model model) {
        Iterable<Like> likes = likesRepository.findAll();
        model.addAttribute("likes", likes);
        return "/posts/comments";
    }

}