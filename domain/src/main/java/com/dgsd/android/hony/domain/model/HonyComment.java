package com.dgsd.android.hony.domain.model;

import com.dgsd.hony.rest.entity.Post;

import java.util.Date;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

import static com.dgsd.android.hony.domain.db.backend.DatabaseBackend.Contract;

@Table(Contract.TABLE_COMMENTS.TABLE_NAME)
public class HonyComment extends Model {

    @Key
    @Column(Contract.TABLE_COMMENTS.COL_ID)
    private String id;

    @Column(Contract.TABLE_COMMENTS.COL_POST_ID)
    private String postId;

    @Column(Contract.TABLE_COMMENTS.COL_MESSAGE)
    private String message;

    @Column(Contract.TABLE_COMMENTS.COL_LIKES)
    private int likes;

    @Column(Contract.TABLE_COMMENTS.COL_CREATED)
    private Date createdTime;

    @Column(Contract.TABLE_COMMENTS.COL_FROM_NAME)
    private String fromName;

    @Column(Contract.TABLE_COMMENTS.COL_FROM_ID)
    private String fromId;

    public HonyComment() {

    }

    HonyComment(Post.Comment comment, String postId) {
        this.id = comment.getId();
        this.message = comment.getMessage();
        this.postId = postId;
        this.likes = comment.getLikeCount();
        this.createdTime = comment.getCreatedTime();
        if (comment.getFrom() != null) {
            this.fromId = comment.getFrom().getId();
            this.fromName = comment.getFrom().getName();
        }
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public int getLikes() {
        return likes;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getFromName() {
        return fromName;
    }

    public String getFromId() {
        return fromId;
    }

    @Override
    public boolean isValid() {
        return super.isValid() && postId != null;
    }
}
