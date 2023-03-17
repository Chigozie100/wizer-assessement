package com.wizer.wizerassessment.exceptions;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ValidationError {

    private String field;
    private String error;
}
