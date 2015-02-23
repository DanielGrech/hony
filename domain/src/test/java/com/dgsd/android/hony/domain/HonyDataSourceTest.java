package com.dgsd.android.hony.domain;

import android.content.Context;

import com.dgsd.android.hony.domain.db.HonyDatabaseProvider;
import com.dgsd.android.hony.domain.db.backend.DatabaseBackend;
import com.dgsd.android.hony.domain.model.HonyComment;
import com.dgsd.android.hony.domain.model.HonyPost;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class HonyDataSourceTest {

    @Test
    public void returnsDatabaseResultsEvenWhenNoServiceResults() {
        final HonyDatabaseProvider dbProvider = new HonyDatabaseProvider(new DatabaseBackend() {
            @Override
            public void save(List<HonyPost> postList) {

            }

            @Override
            public List<HonyPost> getPosts() {
                return null;
            }

            @Override
            public List<HonyComment> getComments(String postId) {
                return null;
            }
        });

        final HonyDataSource dataSource = new HonyDataSource(null, dbProvider);
    }

    private HonyDataSource getLiveDataSource() {
        final HonyDataSource dataSource = new HonyDataSource(Mockito.mock(Context.class));
        dataSource.setAccessToken(TestConfig.ACCESS_TOKEN);
        return dataSource;
    }
}
