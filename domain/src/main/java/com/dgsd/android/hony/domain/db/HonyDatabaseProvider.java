package com.dgsd.android.hony.domain.db;

import com.dgsd.android.hony.domain.db.backend.DatabaseBackend;
import com.dgsd.android.hony.domain.model.HonyComment;
import com.dgsd.android.hony.domain.model.HonyPost;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;

public class HonyDatabaseProvider {

    private final DatabaseBackend dbBackend;

    public HonyDatabaseProvider(DatabaseBackend dbBackend) {
        this.dbBackend = dbBackend;
    }

    public Observable<List<HonyPost>> getPosts() {
        return Observable.defer(new Func0<Observable<List<HonyPost>>>() {
            @Override
            public Observable<List<HonyPost>> call() {
                return Observable.just(dbBackend.getPosts())
                        .filter(new Func1<List<HonyPost>, Boolean>() {
                            @Override
                            public Boolean call(List<HonyPost> honyPosts) {
                                return honyPosts != null;
                            }
                        })
                        .defaultIfEmpty(Collections.EMPTY_LIST);
            }
        });
    }

    public Observable<List<HonyComment>> getComments(final String postId) {
        return Observable.defer(new Func0<Observable<List<HonyComment>>>() {
            @Override
            public Observable<List<HonyComment>> call() {
                return Observable.just(dbBackend.getComments(postId))
                        .filter(new Func1<List<HonyComment>, Boolean>() {
                            @Override
                            public Boolean call(List<HonyComment> comments) {
                                return comments != null;
                            }
                        })
                        .defaultIfEmpty(Collections.EMPTY_LIST);
            }
        });
    }

    public void save(List<HonyPost> postList) {
        if (postList != null && !postList.isEmpty()) {
            dbBackend.save(postList);
        }
    }

}
