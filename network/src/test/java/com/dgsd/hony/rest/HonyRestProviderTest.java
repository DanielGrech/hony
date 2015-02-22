package com.dgsd.hony.rest;

import com.dgsd.hony.rest.entity.GetPostsResponse;
import com.dgsd.hony.rest.exception.HonyConfigException;

import org.junit.Before;
import org.junit.Test;

import rx.Observable;

import static org.assertj.core.api.Assertions.assertThat;

public class HonyRestProviderTest {

    private HonyRestProvider liveProvider;

    @Before
    public void setup() {
        liveProvider = new HonyRestProvider();
        liveProvider.setAccessToken(TestConfig.ACCESS_TOKEN);
    }

    @Test(expected = HonyConfigException.class)
    public void throwsExceptionIfNoAccessTokenSetForGetPosts() {
        new HonyRestProvider().getPosts();
    }

    @Test(expected = HonyConfigException.class)
    public void throwsExceptionIfNoAccessTokenSetForGetPostsByPagingLink() {
        new HonyRestProvider().getPosts("dummy_paging_link");
    }

    @Test
    public void getPostsByPagingLinkReturnsEmptyIfLinkIsNull() {
        final Observable<GetPostsResponse> observable = liveProvider.getPosts(null);
        final boolean isEmpty = observable.isEmpty().toBlocking().single();
        assertThat(isEmpty).isTrue();
    }

    @Test
    public void getPostsRetrievesResults() {
        final Observable<GetPostsResponse> postsObservable = liveProvider.getPosts();
        assertThat(postsObservable).isNotNull();

        final GetPostsResponse getPostsResponse = postsObservable.toBlocking().single();

        assertThat(getPostsResponse).isNotNull();
        assertThat(getPostsResponse.getPagingLinks()).isNotNull();
        assertThat(getPostsResponse.getPosts()).isNotNull().hasSize(25);
    }
}
