package com.jdagger.services;

import org.junit.Test;

import com.jdagger.ApiClient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SdkServiceTest{
    @Test
    public void testFetchData(){
        ApiClient apiClientMock = mock(ApiClient.class);
        when(apiClientMock.getData()).thenReturn("mocked data");

       SdkService sdkService = new SdkService(apiClientMock); 

    String result = sdkService.getData();

        assertEquals("mocked data", result);
     }
}
