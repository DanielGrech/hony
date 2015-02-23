package com.dgsd.android.hony.domain;

import com.dgsd.android.hony.domain.db.HonyDatabaseProvider;
import com.dgsd.android.hony.domain.model.HonyPostList;
import com.dgsd.hony.rest.HonyRestProvider;
import com.dgsd.hony.rest.entity.GetPostsResponse;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

public class HonyDataSource {

    private HonyRestProvider restProvider;
    private HonyDatabaseProvider dbProvider;

    public HonyDataSource() {
        this(new HonyRestProvider(), new HonyDatabaseProvider());
    }

    HonyDataSource(HonyRestProvider restProvider, HonyDatabaseProvider dbProvider) {
        this.restProvider = restProvider;
        this.dbProvider = dbProvider;
    }

    public void setAccessToken(String accessToken) {
        this.restProvider.setAccessToken(accessToken);
    }

    public Observable<HonyPostList> getPosts() {
        final Observable<HonyPostList> restData = restProvider.getPosts()
                .map(new RestToDbPostListMap())
                .map(new SaveRestDataToDbFunction())
                .startWith(HonyPostList.EMPTY);

        final Observable<HonyPostList> dbData
                = dbProvider.getPosts().startWith(HonyPostList.EMPTY);

        return Observable.combineLatest(dbData, restData,
                new Func2<HonyPostList, HonyPostList, HonyPostList>() {
            @Override
            public HonyPostList call(HonyPostList first, HonyPostList second) {
                return first.merge(second);
            }
        });
    }

    private class RestToDbPostListMap implements Func1<GetPostsResponse, HonyPostList> {

        @Override
        public HonyPostList call(GetPostsResponse getPostsResponse) {
            return new HonyPostList(getPostsResponse);
        }
    }

    private class SaveRestDataToDbFunction implements Func1<HonyPostList, HonyPostList> {
        @Override
        public HonyPostList call(HonyPostList input) {
            return input;
        }
    }
}
