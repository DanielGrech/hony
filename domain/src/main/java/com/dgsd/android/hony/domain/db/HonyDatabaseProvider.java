package com.dgsd.android.hony.domain.db;

import com.dgsd.android.hony.domain.model.HonyPostList;

import rx.Observable;

public class HonyDatabaseProvider {

    public Observable<HonyPostList> getPosts() {
        return Observable.empty();
    }
}
