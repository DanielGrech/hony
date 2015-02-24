package com.dgsd.android.hony.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

class IntelligentRecyclerViewHolder<T extends View> extends RecyclerView.ViewHolder {

    public IntelligentRecyclerViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
        this((T) LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    public IntelligentRecyclerViewHolder(T itemView) {
        super(itemView);
    }

    public T getView() {
        return (T) itemView;
    }
}
