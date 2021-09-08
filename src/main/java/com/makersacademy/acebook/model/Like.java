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
    private int userID;
    private int postID;
    private int counter;


    public Like(int userID) {
        this.userID = userID;
    }
}
