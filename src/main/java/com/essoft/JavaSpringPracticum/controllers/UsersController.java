package com.essoft.JavaSpringPracticum.controllers;

import com.essoft.JavaSpringPracticum.business.abstracts.UserService;
import com.essoft.JavaSpringPracticum.business.dtos.CommentListDto;
import com.essoft.JavaSpringPracticum.business.dtos.UserListDto;
import com.essoft.JavaSpringPracticum.business.requests.userRequests.CreateUserRequest;
import com.essoft.JavaSpringPracticum.business.requests.userRequests.UpdateUserRequests;
import com.essoft.JavaSpringPracticum.core.results.DataResult;
import com.essoft.JavaSpringPracticum.core.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1.0/users/")
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getall")
    public DataResult<List<UserListDto>> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("findById/{id}")
    public DataResult<UserListDto> findById(@PathVariable long id) {
        return this.userService.findById(id);
    }

    @GetMapping("findCommentByUserId/{userId}")
    public DataResult<List<CommentListDto>> findCommentByUserId(@PathVariable long userId) {
        return this.userService.findCommentByUserId(userId);
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return this.userService.add(createUserRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateUserRequests updateUserRequests) {
        return this.userService.update(updateUserRequests);
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable long id) {
        return this.userService.delete(id);
    }


}
