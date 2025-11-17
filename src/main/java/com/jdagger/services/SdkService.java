package com.jdagger.services;

import com.jdagger.ApiClient;

public class SdkService{
    private ApiClient apiClient;
    public SdkService(ApiClient apiClient){
        this.apiClient = apiClient;
    }
    
    public String getData(){
        return "hello service";
    }
}
