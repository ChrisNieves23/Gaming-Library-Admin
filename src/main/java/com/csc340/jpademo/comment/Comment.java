/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csc340.jpademo.comment;

/**
 *
 * @author chrisnieves
 */


import com.csc340.jpademo.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Follow Java naming convention for the field name

    @Column(nullable = false)
    private String content;

    private boolean flagged;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user_Id;

    // Constructors, getters, and setters
    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFlagged() { // Follow Java naming convention for boolean getter
        return flagged;
    }

    public void setFlagged(boolean flagged) { // Corrected method name
        this.flagged = flagged;
    }

    public User getUser() {
        return user_Id;
    }

    public void setUser(User user_Id) {
        this.user_Id = user_Id;
    }
}
