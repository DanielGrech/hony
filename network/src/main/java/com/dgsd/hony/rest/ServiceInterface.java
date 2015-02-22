package com.dgsd.hony.rest;

import com.dgsd.hony.rest.entity.GetPostsResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

interface ServiceInterface {

    @GET("/humansofnewyork/posts?fields=id,object_id,message," +
            "type,shares,attachments,comments.summary(true)," +
            "likes.summary(true),created_time,updated_time,link")
    public Observable<GetPostsResponse> getPosts(
            @Query("access_token") String accessToken
    );

    @GET("{link}")
    public Observable<GetPostsResponse> getPosts(
            @Query("access_token") String accessToken,
            @Path("link") String pagingLink
    );
}
