package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Comment;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.CommentsRepository;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class PostsAndCommentsController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentsRepository commentsRepository;

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

    @GetMapping("/posts/comment")
    public String comment(@RequestParam Long id, Model model) {
        Iterable<Comment> comments = commentsRepository.findAllById(Collections.singleton(id));
        Collections.reverse((List<?>) comments);
        Optional<Post> post = postRepository.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        model.addAttribute("id", id);
        return "/posts/comments";
    }
}