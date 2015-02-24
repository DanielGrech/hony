package com.dgsd.hony.rest;

import com.dgsd.hony.rest.entity.GetPostsResponse;
import com.dgsd.hony.rest.exception.HonyConfigException;
import com.dgsd.hony.rest.exception.HonyException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.GsonConverter;
import rx.Observable;

public class HonyRestProvider {

    public enum Logging {
        ON(RestAdapter.LogLevel.FULL),
        OFF(RestAdapter.LogLevel.NONE);

        private final RestAdapter.LogLevel logLevel;

        private Logging(RestAdapter.LogLevel logLevel) {
            this.logLevel = logLevel;
        }
    }

    private ServiceInterface services;

    private String accessToken;

    public HonyRestProvider() {
        this(Logging.ON);
    }

    public HonyRestProvider(Logging logging) {
        final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        services = new RestAdapter.Builder()
                .setLogLevel(logging.logLevel)
                .setConverter(new GsonConverter(gson))
                .setEndpoint(Config.getFacebookEndpoint())
                .setErrorHandler(new ErrorHandler() {
                    @Override
                    public Throwable handleError(RetrofitError cause) {
                        return new HonyException(cause);
                    }
                })
                .build()
                .create(ServiceInterface.class);
    }

    HonyRestProvider(ServiceInterface services) {
        this.services = services;
    }

    public void setAccessToken(String token) {
        this.accessToken = token;
    }

    public Observable<GetPostsResponse> getPosts() {
        assertAccessToken();
        return services.getPosts(accessToken);
    }

    public Observable<GetPostsResponse> getPosts(String pagingLink) {
        assertAccessToken();

        if (pagingLink == null) {
            return Observable.empty();
        } else {
            final String url = pagingLink.replace(Config.getFacebookEndpoint(), "");
            return services.getPosts(accessToken, url);
        }
    }

    private void assertAccessToken() {
        if (accessToken == null) {
            throw new HonyConfigException("Access token not passed. Need to call 'setAccessToken()'");
        }
    }
}
