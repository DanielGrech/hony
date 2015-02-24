package com.dgsd.android.hony.domain.model;

import com.dgsd.hony.rest.entity.Post;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import se.emilsjolander.sprinkles.Model;
import se.emilsjolander.sprinkles.annotations.Column;
import se.emilsjolander.sprinkles.annotations.Key;
import se.emilsjolander.sprinkles.annotations.Table;

import static com.dgsd.android.hony.domain.db.backend.DatabaseBackend.Contract;

@Table(Contract.TABLE_POSTS.TABLE_NAME)
public class HonyPost extends Model {

    @Key
    @Column(Contract.TABLE_POSTS.COL_ID)
    private String id;

    @Column(Contract.TABLE_POSTS.COL_OBJECT_ID)
    private String objectId;

    @Column(Contract.TABLE_POSTS.COL_MESSAGE)
    private String message;

    @Column(Contract.TABLE_POSTS.COL_LINK)
    private String link;

    @Column(Contract.TABLE_POSTS.COL_PHOTO_URL)
    private String photoUrl;

    @Column(Contract.TABLE_POSTS.COL_CREATED)
    private Date createdTime;

    @Column(Contract.TABLE_POSTS.COL_UPDATED)
    private Date updatedTime;

    @Column(Contract.TABLE_POSTS.COL_SHARES)
    private int shares;

    @Column(Contract.TABLE_POSTS.COL_LIKES)
    private int likes;

    @Column(Contract.TABLE_POSTS.COL_COMMENTS)
    private int totalComments;

    private List<HonyComment> comments;

    public HonyPost() {

    }

    public HonyPost(Post networkPost) {
        this.id = networkPost.getId();
        this.message = networkPost.getMessage();
        this.link = networkPost.getLink();
        this.objectId = networkPost.getObjectId();
        this.photoUrl = networkPost.getImageUrl();
        this.createdTime = networkPost.getCreatedTime();
        this.updatedTime = networkPost.getUpdatedTime();
        this.shares = networkPost.getShares();
        this.likes = networkPost.getLikes();
        this.totalComments = networkPost.getCommentCount();

        this.comments = new LinkedList<>();
        final Post.Comments networkComments = networkPost.getComments();
        if (networkComments != null) {
            for (Post.Comment comment : networkComments) {
                comments.add(new HonyComment(comment, this.id));
            }
        }
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

    public int getTotalComments() {
        return totalComments;
    }

    public List<HonyComment> getComments() {
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
                ", objectId='" + objectId + '\'' +
                ", message='" + message +
                '}';
    }
}
