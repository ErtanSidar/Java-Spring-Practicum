package com.essoft.JavaSpringPracticum.business.concretes;

import com.essoft.JavaSpringPracticum.business.abstracts.CommentService;
import com.essoft.JavaSpringPracticum.business.constants.Messages;
import com.essoft.JavaSpringPracticum.business.dtos.CommentListDto;
import com.essoft.JavaSpringPracticum.business.requests.commentRequests.CreateCommentRequest;
import com.essoft.JavaSpringPracticum.business.requests.commentRequests.UpdateCommentRequest;
import com.essoft.JavaSpringPracticum.core.business.BusinessRules;
import com.essoft.JavaSpringPracticum.core.mapping.ModelMapperService;
import com.essoft.JavaSpringPracticum.core.results.*;
import com.essoft.JavaSpringPracticum.entities.Comment;
import com.essoft.JavaSpringPracticum.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentManager implements CommentService {

    private CommentRepository commentRepository;
    private ModelMapperService modelMapperService;

    public CommentManager(CommentRepository commentRepository, ModelMapperService modelMapperService) {
        this.commentRepository = commentRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CommentListDto>> findAll() {
        List<Comment> commentList=this.commentRepository.findAll();
        List<CommentListDto> response =commentList.stream()
                .map(comment -> modelMapperService.forDto().map(comment,CommentListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CommentListDto>>(response);
    }

    @Override
    public DataResult<CommentListDto> findById(long id) {
        Comment comment = this.commentRepository.findById(id).get();
        CommentListDto response = this.modelMapperService.forDto().map(comment,CommentListDto.class);
        return new SuccessDataResult<>(response);
    }


    @Override
    public Result add(CreateCommentRequest createCommentRequest) {
        Comment comment=this.modelMapperService.forRequest().map(createCommentRequest,Comment.class);
        this.commentRepository.save(comment);
        return new SuccessResult(Messages.commentAdded);
    }

    @Override
    public Result update(UpdateCommentRequest updateCommentRequest) {
        Comment comment=this.modelMapperService.forRequest().map(updateCommentRequest,Comment.class);
        this.commentRepository.save(comment);
        return new SuccessResult(Messages.commentUpdated);
    }

    @Override
    public Result delete(long id) {
        Result result= BusinessRules.run(checkIfCommentIdExist(id));
        if (result != null) {
            return result;
        }

        if (commentRepository.existsById(id)) {
            this.commentRepository.deleteById(id);
            return new SuccessResult(Messages.commentDeleted);
        }else {
            return new ErrorResult();
        }

    }

    private Result checkIfCommentIdExist(long id) {
        Comment comment=this.commentRepository.findById(id).get();
        if (comment == null) {
            return new ErrorResult(Messages.commentNotFound);
        }
        return new SuccessResult();
    }
}
