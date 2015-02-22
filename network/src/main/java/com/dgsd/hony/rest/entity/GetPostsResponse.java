package com.dgsd.hony.rest.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPostsResponse extends ApiResponse {

    @SerializedName("data")
    private List<Post> posts;

    @SerializedName("paging")
    private PagingLinks pagingLinks;

    public List<Post> getPosts() {
        return posts;
    }

    public PagingLinks getPagingLinks() {
        return pagingLinks;
    }
}
