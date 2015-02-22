package com.dgsd.hony.rest;

class Config {

    public static final String FACEBOOK_ENDPOINT = "https://graph.facebook.com/";

    public static final String FACEBOOK_API_VERSION = "v2.2";

    private Config() {
        // No-instances..
        throw new RuntimeException("No instances..");
    }

    public static String getFacebookEndpoint() {
        return FACEBOOK_ENDPOINT + FACEBOOK_API_VERSION;
    }
}
