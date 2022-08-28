package com.essoft.JavaSpringPracticum.business.concretes;

import com.essoft.JavaSpringPracticum.business.abstracts.UserService;
import com.essoft.JavaSpringPracticum.business.constants.Messages;
import com.essoft.JavaSpringPracticum.business.dtos.CommentListDto;
import com.essoft.JavaSpringPracticum.business.dtos.UserListDto;
import com.essoft.JavaSpringPracticum.business.requests.userRequests.CreateUserRequest;
import com.essoft.JavaSpringPracticum.business.requests.userRequests.UpdateUserRequests;
import com.essoft.JavaSpringPracticum.core.business.BusinessRules;
import com.essoft.JavaSpringPracticum.core.mapping.ModelMapperService;
import com.essoft.JavaSpringPracticum.core.results.*;
import com.essoft.JavaSpringPracticum.entities.Comment;
import com.essoft.JavaSpringPracticum.entities.User;
import com.essoft.JavaSpringPracticum.repositories.CommentRepository;
import com.essoft.JavaSpringPracticum.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {

    private UserRepository userRepository;
    private ModelMapperService modelMapperService;

    private CommentRepository commentRepository;

    public UserManager(UserRepository userRepository, ModelMapperService modelMapperService, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.modelMapperService = modelMapperService;
        this.commentRepository = commentRepository;
    }

    @Override
    public DataResult<List<UserListDto>> getAll() {
        List<User> userList=userRepository.findAll();
        List<UserListDto> response =userList.stream()
                .map(user -> modelMapperService.forDto().map(user, UserListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<UserListDto>>(response, Messages.usersListed);
    }

    @Override
    public Result add(CreateUserRequest createUserRequest) {
        User user=modelMapperService.forRequest().map(createUserRequest,User.class);
        this.userRepository.save(user);
        return new SuccessResult(Messages.userAdded);
    }

    @Override
    public DataResult<UserListDto> findById(long id) {
        User user=userRepository.findById(id).get();
        UserListDto response = this.modelMapperService.forDto().map(user,UserListDto.class);
        return new SuccessDataResult<>(response, Messages.fetchByUserId);
    }

    @Override
    public DataResult<List<CommentListDto>> findCommentByUserId(long userId) {
        List<Comment> commentList=this.commentRepository.findByUserId(userId);
        List<CommentListDto> response=commentList.stream()
                .map(comment->modelMapperService.forDto().map(comment,CommentListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(response);
    }

    @Override
    public Result delete(long id) {
        Result result = BusinessRules.run(checkIfUserIdExists(id));

        if (result != null) {
            return result;
        }

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new SuccessResult(Messages.userDeleted);
        }else {
            return new ErrorResult();
        }
    }

    @Override
    public Result update(UpdateUserRequests updateUserRequests) {
        User user = this.modelMapperService.forRequest().map(updateUserRequests, User.class);
        this.userRepository.save(user);
        return new SuccessResult(Messages.userUpdated);
    }

    private Result checkIfUserIdExists(long id) {

        User user = userRepository.findById(id).get();

        if (user == null) {
            return new ErrorResult(Messages.userNotFound);
        }
        return new SuccessResult();
    }
}
