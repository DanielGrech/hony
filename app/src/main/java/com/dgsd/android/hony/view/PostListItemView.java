package com.dgsd.android.hony.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgsd.android.hony.R;
import com.dgsd.android.hony.domain.model.HonyPost;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PostListItemView extends CardView {

    @InjectView(R.id.message)
    TextView message;

    @InjectView(R.id.image)
    ImageView image;

    public PostListItemView(Context context) {
        super(context);
    }

    public PostListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PostListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
    }

    public void populate(HonyPost post) {
        message.setText(post.getMessage());

        if (!TextUtils.isEmpty(post.getPhotoUrl())) {
            Picasso.with(getContext()).load(post.getPhotoUrl()).into(image);
        } else {
            // TODO: Show error
            image.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }
}
