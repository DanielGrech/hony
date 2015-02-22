package com.dgsd.hony.rest.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

abstract class ApiResponse {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String toString() {
        return GSON.toJson(this);
    }
}
