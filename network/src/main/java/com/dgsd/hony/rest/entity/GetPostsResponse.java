package com.dgsd.hony.rest.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Iterator;
import java.util.List;

public class GetPostsResponse extends ApiResponse implements Iterable<Post> {

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

    @Override
    public Iterator<Post> iterator() {
        final Iterator<Post> empty = new Iterator<Post>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Post next() {
                return null;
            }
        };

        return posts == null ? empty : posts.iterator();
    }
}
