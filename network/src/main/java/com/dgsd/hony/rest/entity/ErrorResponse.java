package com.dgsd.hony.rest.entity;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse extends ApiResponse {

    @SerializedName("error")
    private Error error;

    @Override
    public String toString() {
        return error == null ? "Unknown" : error.toString();
    }

    private class Error {

        @SerializedName("message")
        private String message;

        @SerializedName("type")
        private String type;

        @SerializedName("code")
        private int code;

        @Override
        public String toString() {
            return String.format("[%s %s] ⇢ %s", code, type, message);
        }
    }
}
