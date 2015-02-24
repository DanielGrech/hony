package com.dgsd.android.hony.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dgsd.android.hony.R;
import com.dgsd.android.hony.domain.model.HonyPost;
import com.dgsd.android.hony.view.PostListItemView;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<IntelligentRecyclerViewHolder<PostListItemView>> {

    private List<HonyPost> posts;

    public void populate(List<HonyPost> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @Override
    public IntelligentRecyclerViewHolder<PostListItemView> onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new IntelligentRecyclerViewHolder(viewGroup, R.layout.li_post);
    }

    @Override
    public int getItemCount() {
        return posts == null ? 0 : posts.size();
    }

    @Override
    public void onBindViewHolder(IntelligentRecyclerViewHolder<PostListItemView> viewHolder, int pos) {
        viewHolder.getView().populate(posts.get(pos));
    }
}