package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;


@Data
@Entity
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;
    private long postID;
    private long counter;


    public Like(long userID, long postID, long counter) {
        this.userID = userID;
        this.postID = postID;
        this.counter = counter;
    }
}
