package com.essoft.JavaSpringPracticum.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentListDto {

    private long id;

    private String comment;

    private LocalDate commentDate;

    private long userId;

    private long productId;
}
