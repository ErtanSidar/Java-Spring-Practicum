package com.essoft.JavaSpringPracticum.business.abstracts;

import com.essoft.JavaSpringPracticum.business.dtos.CommentListDto;
import com.essoft.JavaSpringPracticum.business.requests.commentRequests.CreateCommentRequest;
import com.essoft.JavaSpringPracticum.business.requests.commentRequests.UpdateCommentRequest;
import com.essoft.JavaSpringPracticum.core.results.DataResult;
import com.essoft.JavaSpringPracticum.core.results.Result;

import java.time.LocalDate;
import java.util.List;

public interface CommentService {
    DataResult<List<CommentListDto>> findAll();

    DataResult<CommentListDto> findById(long id);

    Result add(CreateCommentRequest createCommentRequest);

    Result update(UpdateCommentRequest updateCommentRequest);

    Result delete(long id);

}
