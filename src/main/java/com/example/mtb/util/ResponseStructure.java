package com.example.mtb.util;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseStructure<T> {

    private int statusCode;
    private String message;
    private T data;

}
