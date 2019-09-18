package com.zlyj.resttemplate.movie.util;

import com.digidite.core.exception.ErrorCode;

public enum ApiErrorCodeUtil implements ErrorCode {

    GG_ERROR("10002", "失败"),

    OPERATION_ERROR("10011","操作失败");


    private String code;
    private String message;

    ApiErrorCodeUtil(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
