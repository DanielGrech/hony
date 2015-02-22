package com.dgsd.hony.rest.exception;

import com.dgsd.hony.rest.entity.ErrorResponse;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class HonyException extends RuntimeException {

    private int status;

    private String url;

    private ErrorResponse error;

    public HonyException(RetrofitError cause) {
        super(cause);

        final Response response = cause.getResponse();
        this.status = response.getStatus();
        this.url = response.getUrl();

        this.error = (ErrorResponse) cause.getBodyAs(ErrorResponse.class);
    }

    @Override
    public String getMessage() {
        if (error == null) {
            return String.format("%s \u21E2 %s", url, status);
        } else {
            return error.toString();
        }
    }

    public int getStatus() {
        return status;
    }

    public String getUrl() {
        return url;
    }
}