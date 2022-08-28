package com.essoft.JavaSpringPracticum.controllers;

import com.essoft.JavaSpringPracticum.business.abstracts.CommentService;
import com.essoft.JavaSpringPracticum.business.dtos.CommentListDto;
import com.essoft.JavaSpringPracticum.business.requests.commentRequests.CreateCommentRequest;
import com.essoft.JavaSpringPracticum.business.requests.commentRequests.UpdateCommentRequest;
import com.essoft.JavaSpringPracticum.core.results.DataResult;
import com.essoft.JavaSpringPracticum.core.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1.0/comments/")
public class CommentsController {

    private CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("getall")
    public DataResult<List<CommentListDto>> findAll() {
        return this.commentService.findAll();
    }

    @GetMapping("findbyId/{id}")
    public DataResult<CommentListDto> findById(@PathVariable long id) {
        return this.commentService.findById(id);
    }


    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateCommentRequest createCommentRequest) {
        return this.commentService.add(createCommentRequest);
    }

    @PostMapping("update")
    public Result update(@RequestBody UpdateCommentRequest updateCommentRequest) {
        return this.commentService.update(updateCommentRequest);
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable long id) {
        return this.commentService.delete(id);
    }
}
