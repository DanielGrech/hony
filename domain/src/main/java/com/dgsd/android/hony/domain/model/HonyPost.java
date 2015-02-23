package com.dgsd.android.hony.domain.model;

import com.dgsd.hony.rest.entity.Post;

import java.util.Comparator;
import java.util.Date;

public class HonyPost {

    private String id;

    private String message;

    private String link;

    private String objectId;

    private String photoUrl;

    private Date createdTime;

    private Date updatedTime;

    private int shares;

    private int likes;

    private int comments;

    HonyPost(Post networkPost) {
        this.id = networkPost.getId();
        this.message = networkPost.getMessage();
        this.link = networkPost.getLink();
        this.objectId = networkPost.getObjectId();
        this.photoUrl = networkPost.getImageUrl();
        this.createdTime = networkPost.getCreatedTime();
        this.updatedTime = networkPost.getUpdatedTime();
        this.shares = networkPost.getShares();
        this.likes = networkPost.getLikes();
        this.comments = networkPost.getCommentCount();
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getLink() {
        return link;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public int getShares() {
        return shares;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }

    public static final Comparator<HonyPost> SORT_BY_NEWEST_COMPARATOR = new Comparator<HonyPost>() {
        @Override
        public int compare(HonyPost lhs, HonyPost rhs) {
            if (lhs.updatedTime == null) {
                return 1;
            } else if (rhs.updatedTime == null) {
                return -1;
            } else {
                return lhs.updatedTime.compareTo(rhs.updatedTime);
            }
        }
    };

    @Override
    public String toString() {
        return "HonyPost{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", link='" + link + '\'' +
                ", objectId='" + objectId + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", shares=" + shares +
                ", likes=" + likes +
                ", comments=" + comments +
                '}';
    }
}
