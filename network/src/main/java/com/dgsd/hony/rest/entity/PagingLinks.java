package com.dgsd.hony.rest.entity;

import com.google.gson.annotations.SerializedName;

public class PagingLinks {

    @SerializedName("next")
    private String nextLink;

    @SerializedName("previous")
    private String previousLink;

    public String getNextLink() {
        return nextLink;
    }

    public String getPreviousLink() {
        return previousLink;
    }
}
