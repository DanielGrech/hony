package com.dgsd.hony.rest.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Post {

    @SerializedName("id")
    private String id;

    @SerializedName("message")
    private String message;

    @SerializedName("link")
    private String link;

    @SerializedName("type")
    private String type;

    @SerializedName("object_id")
    private String objectId;

    @SerializedName("created_time")
    private Date createdTime;

    @SerializedName("updated_time")
    private Date updatedTime;

    @SerializedName("shares")
    private Shares shares;

    @SerializedName("likes")
    private Likes likes;

    @SerializedName("comments")
    private Comments comments;

    @SerializedName("attachments")
    private Attachments attachments;

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getLink() {
        return link;
    }

    public String getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public int getShares() {
        return shares == null ? 0 : shares.getCount();
    }

    public int getLikes() {
        return likes == null ? 0 : likes.getTotalCount();
    }

    public int getCommentCount() {
        return comments == null ? 0 : comments.getTotalCount();
    }

    public Comments getComments() {
        return comments;
    }

    public String getImageUrl() {
        return attachments.getImageUrl();
    }

    public class Shares {

        @SerializedName("count")
        private int count;

        public int getCount() {
            return count;
        }
    }

    public class Comments {

        @SerializedName("data")
        private List<Comment> comments;

        @SerializedName("summary")
        private Summary summary;

        public List<Comment> getComments() {
            return comments;
        }

        public int getTotalCount() {
            return summary == null ? 0 : summary.getCount();
        }
    }

    public class Comment {

        @SerializedName("id")
        private String id;

        @SerializedName("message")
        private String message;

        @SerializedName("like_count")
        private int likeCount;

        @SerializedName("created_time")
        private Date createdTime;

        @SerializedName("from")
        private CommentFrom from;

        public String getId() {
            return id;
        }

        public String getMessage() {
            return message;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public Date getCreatedTime() {
            return createdTime;
        }

        public CommentFrom getFrom() {
            return from;
        }
    }

    public class CommentFrom {

        @SerializedName("id")
        private String id;

        @SerializedName("name")
        private String name;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public class Likes {

        @SerializedName("data")
        private List<Like> likes;

        @SerializedName("summary")
        private Summary summary;

        public List<Like> getLikes() {
            return likes;
        }

        public int getTotalCount() {
            return summary == null ? 0 : summary.getCount();
        }
    }

    public class Like {

        @SerializedName("id")
        private String id;

        @SerializedName("name")
        private String name;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    private class Attachments {

        @SerializedName("data")
        private List<Attachment> attachments;

        public String getImageUrl() {
            return attachments == null || attachments.isEmpty() ?
                    null : attachments.get(0).getImageUrl();
        }
    }

    private class Attachment {

        @SerializedName("media")
        AttachmentMedia media;

        public String getImageUrl() {
            return media == null ? null : media.getImageUrl();
        }

    }

    private class AttachmentMedia {

        @SerializedName("image")
        AttachmentImage image;

        public String getImageUrl() {
            return image == null ? null : image.getSource();
        }
    }

    private class AttachmentImage {

        @SerializedName("src")
        String source;

        public String getSource() {
            return source;
        }
    }

    private class Summary {

        @SerializedName("total_count")
        private int count;

        public int getCount() {
            return count;
        }
    }
}
