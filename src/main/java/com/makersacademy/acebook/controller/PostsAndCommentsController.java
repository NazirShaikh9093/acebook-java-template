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
import org.thymeleaf.util.ArrayUtils;

import java.util.ArrayList;
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
    public RedirectView delete(@RequestParam long postid) {
        postRepository.deleteById(postid);
        deletePostRespectiveComments(postid);
        return new RedirectView("/posts");
    }

    @GetMapping("/posts/comments")
    public String comment(@RequestParam long postid, @RequestParam String postContent, Model model) {
        ArrayList<Comment> comments = getPostRespectiveComments(postid);
        Collections.reverse(comments);
        model.addAttribute("postContent", postContent);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", new Comment());
        model.addAttribute("postid", postid);
        return "/posts/comments";
    }

    @PostMapping("/posts/comment")
    public RedirectView newComment(@ModelAttribute Comment comment, @RequestParam long postid) {
        comment.addPostId(postid);
        commentsRepository.save(comment);
        return new RedirectView("/posts");
    }

    @DeleteMapping("/comment/delete")
    public RedirectView deleteComment(@RequestParam long commentid) {
        commentsRepository.deleteById(commentid);
        return new RedirectView("/posts");
    }

    public ArrayList<Comment> getPostRespectiveComments(long postid) {
        Iterable<Comment> comments = commentsRepository.findAll();
        ArrayList<Comment> foundComments = new ArrayList<>();
        for (Comment comment: comments) {
            if(comment.getPostId() == postid) {
                foundComments.add(comment);
            }
        }
        return foundComments;
    }

    public void deletePostRespectiveComments(long postid) {
        Iterable<Comment> comments = commentsRepository.findAll();
        for (Comment comment: comments) {
            if(comment.getPostId() == postid) {
                commentsRepository.delete(comment);
            }
        }
    }
}