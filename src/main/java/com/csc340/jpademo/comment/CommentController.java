/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csc340.jpademo.comment;

/**
 *
 * @author chrisnieves
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public String showFlaggedComments(Model model) {
        List<Comment> flaggedComments = commentService.getAllFlaggedComments();
        model.addAttribute("flaggedComments", flaggedComments);
        return "comment/flagged-comments"; // Thymeleaf template to display flagged comments
    }

    @PutMapping("/edit/{comment_id}")
public String showEditFlaggedForm(@PathVariable("comment_id") Long commentId, Model model) {
    Optional<Comment> comment = commentService.getCommentById(commentId);
    comment.ifPresent(value -> model.addAttribute("comment", value));
    return "comment/edit-flagged-status"; // Thymeleaf template to edit flagged comment status
}

@PostMapping("/edit/{comment_id}")
public String editFlaggedStatus(@PathVariable("comment_id") Long commentId, @RequestParam("flagged") boolean flagged) {
    Optional<Comment> optionalComment = commentService.getCommentById(commentId);
    optionalComment.ifPresent(comment -> {
        comment.setFlagged(flagged);
        commentService.saveComment(comment);
    });
    return "redirect:/comment/all"; // Redirect back to flagged comments list
}



    @DeleteMapping("/delete/{comment_id}")
    public String deleteComment(@PathVariable("comment_id") Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/comment/all"; // Redirect back to flagged comments list
    }
}
