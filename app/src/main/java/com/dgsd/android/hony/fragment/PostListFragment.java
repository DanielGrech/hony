package com.dgsd.android.hony.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dgsd.android.hony.R;
import com.dgsd.android.hony.domain.HonyDataSource;
import com.dgsd.android.hony.domain.model.HonyPost;
import com.dgsd.android.hony.mvp.presenter.PostListPresenter;
import com.dgsd.android.hony.mvp.view.PostListMVPView;

import java.util.List;

import butterknife.InjectView;

public class PostListFragment extends BaseFragment implements PostListMVPView {

    @InjectView(R.id.refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    @InjectView(R.id.fullscreen_error)
    TextView fullscreenError;

    private PostListPresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final HonyDataSource source = new HonyDataSource(getActivity());
        source.setAccessToken("CAACEdEose0cBAIM81WaKADuEN5mgGu9nlS3vXd7MCQdS4YbkFfPmr0gfACT2CpkPF3RL4TZBOOWCi9cUDVJSuY9BenxYWhhHBDH1a78ugGLum5LJSxxvwniR4J6TTHRWaujyZCL6Jeqtd6uDV6QbzOu8KQzJNKKy5IFmkyEWSUz9lQvfJeijZAwyZBBfYIYpvfSI7ZCVMQuPK5Glvoj1Tb3QjxDvTrZC0ZD");

        presenter = new PostListPresenter(this, source);
        presenter.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_post_list;
    }

    @Override
    protected void onCreateView(View rootView, Bundle savedInstanceState) {
        super.onCreateView(rootView, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void showError(@NonNull String error) {
        Toast.makeText(getContext(), "ERROR: " + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFullscreenError(@NonNull String error) {
        fullscreenError.setText("ERROR: " + error);
    }

    @Override
    public void showPosts(@NonNull List<HonyPost> posts) {
        fullscreenError.setText("Show posts: " + posts);
    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
