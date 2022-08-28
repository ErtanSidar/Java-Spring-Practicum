package com.essoft.JavaSpringPracticum.controllers;

import com.essoft.JavaSpringPracticum.business.abstracts.ProductService;
import com.essoft.JavaSpringPracticum.business.dtos.CommentListDto;
import com.essoft.JavaSpringPracticum.business.dtos.ProductListDto;
import com.essoft.JavaSpringPracticum.business.requests.productRequests.CreateProductRequest;
import com.essoft.JavaSpringPracticum.business.requests.productRequests.UpdateProductRequest;
import com.essoft.JavaSpringPracticum.core.results.DataResult;
import com.essoft.JavaSpringPracticum.core.results.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/1.0/products/")
public class ProductsController {

    private ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("getall")
    public DataResult<List<ProductListDto>> findAll() {
        return this.productService.findAll();
    }

    @GetMapping("findById/{id}")
    public DataResult<ProductListDto> findById(@PathVariable long id) {
        return this.productService.findById(id);
    }

    @GetMapping("findCommentByProductId/{productId}")
    public DataResult<List<CommentListDto>> findByProductId(@PathVariable long productId) {
        return this.productService.findByCommentWithProductId(productId);
    }

    @GetMapping("findCommentByDate")
    public DataResult<List<CommentListDto>> findCommentByDate(@RequestParam long productId, @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endedDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endedDate) {
        return this.productService.findCommentByDate(productId,startDate,endedDate);
    }

    @GetMapping("findCommentByDateWithUser")
    public DataResult<List<CommentListDto>> findCommentByDateWithUser(@RequestParam long userId, @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("endedDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endedDate) {
        return this.productService.findCommentByDateWithUser(userId,startDate,endedDate);
    }

    @GetMapping("expiryDateExpired")
    public DataResult<List<ProductListDto>> pastExpirationDate() {
        return this.productService.getPastExpirationDate();
    }

    @GetMapping("expiryDateNotExpired")
    public DataResult<List<ProductListDto>> expiryDateNotExpired() {
        return this.productService.expiryDateNotExpired();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateProductRequest createProductRequest) {
        return this.productService.add(createProductRequest);
    }

    @PostMapping("update")
    public Result update(@RequestBody UpdateProductRequest updateProductRequest) {
        return this.productService.update(updateProductRequest);
    }

    @DeleteMapping("delete/{id}")
    public Result deleteById(@PathVariable long id) {
        return this.productService.deleteById(id);
    }

}
