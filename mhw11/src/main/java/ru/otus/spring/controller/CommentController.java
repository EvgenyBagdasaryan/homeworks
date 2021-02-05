package ru.otus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.exceptions.DocumentNotFoundException;
import ru.otus.spring.service.CommentService;

import java.util.List;

@Controller
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public String listPage(@RequestParam("bookId") String bookId, @RequestParam(required = false) String id, Model model) throws DocumentNotFoundException {

        if(id != null){
            commentService.deleteById(id);
        }
        List<Comment> comments = commentService.getCommentsByBookId(bookId);
        model.addAttribute("comments", comments);
        model.addAttribute("bookId", bookId);
        return "list_of_comments";
    }

    @GetMapping("/comment")
    public String editComment(@RequestParam String bookId, @RequestParam(required = false) String id, Model model) throws DocumentNotFoundException {

        Comment comment = null;
        if(id != null){
            comment = commentService.findByCommentId(id);
        }else{
            comment = new Comment();
        }
        model.addAttribute("comment", comment);
        model.addAttribute("bookId", bookId);
        return "selected_comment";
    }
}