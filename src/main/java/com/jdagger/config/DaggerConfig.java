package com.jdagger.config;

public class DaggerConfig{
    private final String apiKey;

    public DaggerConfig(String apiKey){
        this.apiKey = apiKey;
    }

    public String getApiKey(){
        return this.apiKey;
    }
}
