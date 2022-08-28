package com.essoft.JavaSpringPracticum.business.abstracts;

import com.essoft.JavaSpringPracticum.business.dtos.CommentListDto;
import com.essoft.JavaSpringPracticum.business.dtos.ProductListDto;
import com.essoft.JavaSpringPracticum.business.requests.productRequests.CreateProductRequest;
import com.essoft.JavaSpringPracticum.business.requests.productRequests.UpdateProductRequest;
import com.essoft.JavaSpringPracticum.core.results.DataResult;
import com.essoft.JavaSpringPracticum.core.results.Result;
import com.essoft.JavaSpringPracticum.entities.Product;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    DataResult<List<ProductListDto>> findAll();

    DataResult<ProductListDto> findById(long id);

    Result add(CreateProductRequest createProductRequest);

    Result update(UpdateProductRequest updateProductRequest);

    Result deleteById(long id);

    DataResult<List<CommentListDto>> findByCommentWithProductId(long productId);

    DataResult<List<ProductListDto>> getPastExpirationDate();

    DataResult<List<ProductListDto>> expiryDateNotExpired();

    DataResult<List<CommentListDto>> findCommentByDate(long productId, LocalDate startDate, LocalDate endedDate);

    DataResult<List<CommentListDto>> findCommentByDateWithUser(long userId, LocalDate startDate, LocalDate endedDate);
}
