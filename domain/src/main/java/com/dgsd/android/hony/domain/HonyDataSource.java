package com.dgsd.android.hony.domain;

import android.content.Context;

import com.dgsd.android.hony.domain.db.HonyDatabaseProvider;
import com.dgsd.android.hony.domain.db.backend.SprinklesDatabaseBackend;
import com.dgsd.android.hony.domain.model.HonyPost;
import com.dgsd.hony.rest.HonyRestProvider;
import com.dgsd.hony.rest.entity.GetPostsResponse;
import com.dgsd.hony.rest.entity.Post;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class HonyDataSource {

    private HonyRestProvider restProvider;
    private HonyDatabaseProvider dbProvider;

    public HonyDataSource(Context context) {
        this(new HonyRestProvider(),
                new HonyDatabaseProvider(SprinklesDatabaseBackend.getInstance(context)));
    }

    HonyDataSource(HonyRestProvider restProvider, HonyDatabaseProvider dbProvider) {
        this.restProvider = restProvider;
        this.dbProvider = dbProvider;
    }

    public void setAccessToken(String accessToken) {
        this.restProvider.setAccessToken(accessToken);
    }

    public Observable<List<HonyPost>> getPosts() {
        final Observable<List<HonyPost>> restData = restProvider.getPosts()
                .map(new RestToDbPostListMap())
                .map(new SaveRestDataToDbFunction());

        final Observable<List<HonyPost>> dbData = dbProvider.getPosts();

        return Observable.mergeDelayError(dbData, restData)
                .filter(new Func1<List<HonyPost>, Boolean>() {
                    @Override
                    public Boolean call(List<HonyPost> honyPosts) {
                        return honyPosts != null;
                    }
                })
                .map(new Func1<List<HonyPost>, List<HonyPost>>() {
                    @Override
                    public List<HonyPost> call(List<HonyPost> honyPosts) {
                        Collections.sort(honyPosts, HonyPost.SORT_BY_NEWEST_COMPARATOR);
                        return honyPosts;
                    }
                })
                .defaultIfEmpty(Collections.EMPTY_LIST);
    }

    private class RestToDbPostListMap implements Func1<GetPostsResponse, List<HonyPost>> {

        @Override
        public List<HonyPost> call(GetPostsResponse getPostsResponse) {
            final List<HonyPost> posts = new LinkedList<>();
            if (getPostsResponse != null) {
                for (Post post : getPostsResponse) {
                    posts.add(new HonyPost(post));
                }
            }
            return posts;
        }
    }

    private class SaveRestDataToDbFunction implements Func1<List<HonyPost>, List<HonyPost>> {
        @Override
        public List<HonyPost> call(List<HonyPost> input) {
            dbProvider.save(input);
            return input;
        }
    }
}
