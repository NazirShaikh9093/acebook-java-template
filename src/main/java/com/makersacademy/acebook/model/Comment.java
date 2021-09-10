package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long postid;
    private String content;

    public Comment() {}

    public Comment(String content) { this.content = content; }

    public void addPostId(long postid) {
        this.postid = postid;
    }

    public long getPostId() {
        return this.postid;
    }
}