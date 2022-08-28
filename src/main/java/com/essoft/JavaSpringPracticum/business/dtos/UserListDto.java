package com.essoft.JavaSpringPracticum.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListDto {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private long phoneNumber;

}
