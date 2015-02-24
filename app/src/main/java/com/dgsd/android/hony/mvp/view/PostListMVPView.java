package com.dgsd.android.hony.mvp.view;

import android.support.annotation.NonNull;

import com.dgsd.android.hony.domain.model.HonyPost;

import java.util.List;

public interface PostListMVPView extends MVPView {

    public void showLoading();

    public void hideLoading();

    public void showError(@NonNull String error);

    public void showFullscreenError(@NonNull String error);

    public void showPosts(@NonNull List<HonyPost> posts);
}
