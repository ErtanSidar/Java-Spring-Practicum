package com.essoft.JavaSpringPracticum.business.concretes;

import com.essoft.JavaSpringPracticum.business.abstracts.ProductService;
import com.essoft.JavaSpringPracticum.business.constants.Messages;
import com.essoft.JavaSpringPracticum.business.dtos.CommentListDto;
import com.essoft.JavaSpringPracticum.business.dtos.ProductListDto;
import com.essoft.JavaSpringPracticum.business.requests.productRequests.CreateProductRequest;
import com.essoft.JavaSpringPracticum.business.requests.productRequests.UpdateProductRequest;
import com.essoft.JavaSpringPracticum.core.business.BusinessRules;
import com.essoft.JavaSpringPracticum.core.mapping.ModelMapperService;
import com.essoft.JavaSpringPracticum.core.results.*;
import com.essoft.JavaSpringPracticum.entities.Comment;
import com.essoft.JavaSpringPracticum.entities.Product;
import com.essoft.JavaSpringPracticum.repositories.CommentRepository;
import com.essoft.JavaSpringPracticum.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService {

    private ProductRepository productRepository;
    private ModelMapperService modelMapperService;
    private CommentRepository commentRepository;

    public ProductManager(ProductRepository productRepository, ModelMapperService modelMapperService, CommentRepository commentRepository) {
        this.productRepository = productRepository;
        this.modelMapperService = modelMapperService;
        this.commentRepository = commentRepository;
    }

    @Override
    public DataResult<List<ProductListDto>> findAll() {
        List<Product> productList =this.productRepository.findAll();
        List<ProductListDto> response = productList.stream()
                .map(product ->modelMapperService.forDto().map(product,ProductListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ProductListDto>>(response, Messages.productsListed);
    }

    @Override
    public DataResult<ProductListDto> findById(long id) {
        Product product = this.productRepository.findById(id).get();
        ProductListDto response = this.modelMapperService.forDto().map(product,ProductListDto.class);
        return new SuccessDataResult<>(response,Messages.userFindById);
    }

    @Override
    public DataResult<List<CommentListDto>> findByCommentWithProductId(long productId) {
        List<Comment> commentList =this.commentRepository.findByProductId(productId);
        List<CommentListDto> response=commentList.stream()
                .map(comment->modelMapperService.forDto().map(comment,CommentListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<List<ProductListDto>> getPastExpirationDate() {
        List<Product> productList=this.productRepository.findAll();
        List<Product> expDate=productList.stream().filter(product->product.getExpirationDate().isBefore(LocalDate.now())).collect(Collectors.toList());
        List<ProductListDto> response =expDate.stream()
                .map(product->modelMapperService.forDto().map(product,ProductListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<List<ProductListDto>> expiryDateNotExpired() {
        List<Product> productList=this.productRepository.findAll();
        List<Product> notExpDate=productList.stream().filter(product->product.getExpirationDate().isAfter(LocalDate.now()) || product.getExpirationDate().equals(null)).collect(Collectors.toList());
        List<ProductListDto> response=notExpDate.stream()
                .map(product->modelMapperService.forDto().map(product,ProductListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<List<CommentListDto>> findCommentByDate(long productId, LocalDate startDate, LocalDate endedDate) {
        List<Comment> commentList=this.commentRepository.findByProductId(productId);
        List<Comment> betweenDate=commentList.stream().filter(comment -> comment.getCommentDate().isBefore(endedDate) && comment.getCommentDate().isAfter(startDate)).collect(Collectors.toList());
        List<CommentListDto> response = betweenDate.stream()
                .map(comment->modelMapperService.forDto().map(comment,CommentListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<List<CommentListDto>> findCommentByDateWithUser(long userId, LocalDate startDate, LocalDate endedDate) {
        List<Comment> userComment=this.commentRepository.findByUserId(userId);
        List<Comment> commentList=userComment.stream().filter(comment -> comment.getCommentDate().isBefore(endedDate) && comment.getCommentDate().isAfter(startDate)).collect(Collectors.toList());
        List<CommentListDto> response=commentList.stream()
                .map(comment->modelMapperService.forDto().map(comment,CommentListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(response);
    }

    @Override
    public Result add(CreateProductRequest createProductRequest) {
        Product product = modelMapperService.forRequest().map(createProductRequest,Product.class);
        this.productRepository.save(product);
        return new SuccessResult(Messages.productAdded);
    }

    @Override
    public Result update(UpdateProductRequest updateProductRequest) {
        Product product = modelMapperService.forRequest().map(updateProductRequest,Product.class);
        this.productRepository.save(product);
        return new SuccessResult(Messages.productUpdated);
    }

    @Override
    public Result deleteById(long id) {

        Result result = BusinessRules.run(checkIfProductIdExists(id));

        if (result != null) {
            return result;
        }
        if (productRepository.existsById(id)) {
            this.productRepository.deleteById(id);
            return new SuccessResult(Messages.productDeleted);
        }else {
            return new ErrorResult();
        }
    }

    private Result checkIfProductIdExists(long id) {

        Product product = productRepository.findById(id).get();

        if (product == null) {
            return new ErrorResult(Messages.productNotFound);
        }
        return new SuccessResult();
    }
}
