package com.dgsd.android.hony.mvp.presenter;

import android.support.annotation.NonNull;

import com.dgsd.android.hony.domain.HonyDataSource;
import com.dgsd.android.hony.domain.model.HonyPost;
import com.dgsd.android.hony.mvp.view.PostListMVPView;

import java.util.List;

import rx.Observer;

public class PostListPresenter extends Presenter<PostListMVPView> {

    private final HonyDataSource dataSource;

    public PostListPresenter(@NonNull PostListMVPView view, @NonNull HonyDataSource dataSource) {
        super(view);
        this.dataSource = dataSource;
    }

    @Override
    public void onResume() {
        super.onResume();

        getView().showLoading();
        subscribe(dataSource.getPosts(), new Observer<List<HonyPost>>() {
            @Override
            public void onCompleted() {
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoading();
                getView().showError("Error!");
            }

            @Override
            public void onNext(List<HonyPost> honyPosts) {
                getView().showPosts(honyPosts);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
