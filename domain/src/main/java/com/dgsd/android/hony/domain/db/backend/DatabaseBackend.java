package com.dgsd.android.hony.domain.db.backend;

import com.dgsd.android.hony.domain.model.HonyComment;
import com.dgsd.android.hony.domain.model.HonyPost;

import java.util.List;

public interface DatabaseBackend {

    public void save(List<HonyPost> postList);

    public List<HonyPost> getPosts();

    public List<HonyComment> getComments(String postId);

    public static final class Contract {

        private Contract() {
            throw new IllegalStateException("No instances..");
        }

        public static final class TABLE_POSTS {

            public static final String TABLE_NAME = "posts";

            public static final String COL_ID = "id";
            public static final String COL_OBJECT_ID = "object_id";
            public static final String COL_MESSAGE = "message";
            public static final String COL_LINK = "link";
            public static final String COL_PHOTO_URL = "photo_url";
            public static final String COL_CREATED = "created";
            public static final String COL_UPDATED = "updated";
            public static final String COL_SHARES = "shares";
            public static final String COL_LIKES = "likes";
            public static final String COL_COMMENTS = "comments";
        }

        public static final class TABLE_COMMENTS {
            public static final String TABLE_NAME = "comments";

            public static final String COL_ID = "id";
            public static final String COL_POST_ID = "post_id";
            public static final String COL_MESSAGE = "message";
            public static final String COL_CREATED = "created";
            public static final String COL_LIKES = "likes";
            public static final String COL_FROM_ID = "from_id";
            public static final String COL_FROM_NAME = "from_name";
        }
    }
}
