package com.essoft.JavaSpringPracticum.business.requests.userRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequests {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

}
