package com.essoft.JavaSpringPracticum.business.requests.commentRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentRequest {

    private long id;

    private String comment;

    private LocalDate commentDate;

    private long userId;

    private long productId;
}
