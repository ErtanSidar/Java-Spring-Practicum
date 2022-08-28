package com.essoft.JavaSpringPracticum.core.business;

import com.essoft.JavaSpringPracticum.core.results.Result;

public class BusinessRules {

    public static Result run(Result ...logics) {
        for (Result logic : logics) {
            if(!logic.isSuccess()) {
                return logic;
            }
        }
        return null;
    }

}
