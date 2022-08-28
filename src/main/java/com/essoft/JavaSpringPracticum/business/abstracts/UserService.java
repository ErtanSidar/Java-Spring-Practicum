package com.essoft.JavaSpringPracticum.business.abstracts;

import com.essoft.JavaSpringPracticum.business.dtos.CommentListDto;
import com.essoft.JavaSpringPracticum.business.dtos.UserListDto;
import com.essoft.JavaSpringPracticum.business.requests.userRequests.CreateUserRequest;
import com.essoft.JavaSpringPracticum.business.requests.userRequests.UpdateUserRequests;
import com.essoft.JavaSpringPracticum.core.results.DataResult;
import com.essoft.JavaSpringPracticum.core.results.Result;

import java.util.List;

public interface UserService {
    DataResult<List<UserListDto>> getAll();

    Result add(CreateUserRequest createUserRequest);

    DataResult<UserListDto> findById(long id);

    Result delete(long id);

    Result update(UpdateUserRequests updateUserRequests);

    DataResult<List<CommentListDto>> findCommentByUserId(long userId);
}
