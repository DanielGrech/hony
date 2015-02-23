package com.dgsd.android.hony.domain.db.backend;

import com.dgsd.android.hony.domain.model.HonyComment;
import com.dgsd.android.hony.domain.model.HonyPost;

import java.util.List;

public class MemoryResidentBackend implements DatabaseBackend {

    private List<HonyPost> storage;

    @Override
    public void save(List<HonyPost> postList) {
        storage = postList;
    }

    @Override
    public List<HonyPost> getPosts() {
        return storage;
    }

    @Override
    public List<HonyComment> getComments(String postId) {
        return null;
    }
}
