package com.dgsd.android.hony.domain;

public class HonyDataSourceTest {

    private HonyDataSource getLiveDataSource() {
        final HonyDataSource dataSource = new HonyDataSource();
        dataSource.setAccessToken(TestConfig.ACCESS_TOKEN);
        return dataSource;
    }
}
