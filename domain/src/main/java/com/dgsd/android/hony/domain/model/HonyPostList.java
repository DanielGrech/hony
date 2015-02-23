package com.dgsd.android.hony.domain.model;

import com.dgsd.hony.rest.entity.GetPostsResponse;
import com.dgsd.hony.rest.entity.Post;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class HonyPostList {

    public static final HonyPostList EMPTY = new HonyPostList();

    private List<HonyPost> posts;
    private Set<String> existingPostIds;

    HonyPostList() {
        posts = new LinkedList<>();
        existingPostIds = new HashSet<>();
    }

    private HonyPostList(HonyPostList copy) {
        this();
        for (HonyPost post : copy.posts) {
            add(post);
        }
    }

    public HonyPostList(GetPostsResponse getPostsResponse) {
        this();
        if (getPostsResponse != null && getPostsResponse.getPosts() != null) {
            for (Post post : getPostsResponse.getPosts()) {
                add(new HonyPost(post));
            }
        }
    }

    void add(HonyPost post) {
        if (!existingPostIds.contains(post.getId())) {
            posts.add(post);
            existingPostIds.add(post.getId());
        }
    }

    private void sortPosts() {
        Collections.sort(this.posts, HonyPost.SORT_BY_NEWEST_COMPARATOR);
    }

    public HonyPostList merge(HonyPostList postList) {
        final HonyPostList retval = new HonyPostList(this);

        for (HonyPost post : postList.posts) {
            retval.add(post);
        }

        retval.sortPosts();

        return retval;
    }

    @Override
    public String toString() {
        return posts == null ? "empty" : posts.toString();
    }
}
