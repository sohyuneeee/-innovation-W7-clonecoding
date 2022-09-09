package com.example.clonecoding.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto<T> {
    private boolean isSuccess;
    private T data;

    public static <T> ResponseDto<T> isSuccess(T data) {
        return new ResponseDto<>(true, data);
    }

    public static <T> ResponseDto<T> isFail(T message) {
        return new ResponseDto<>(false, message);
    }
}
