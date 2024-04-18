package com.jdagger;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.junit.Test;

import com.jdagger.builder.QueryBuilder;
import com.jdagger.builder.QueryBuilderImpl;
import com.jdagger.dto.Query;
import com.jdagger.exception.ContainerException;

/**
 * Unit test for simple App.
 */
public class ClientSdkDaggerTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void should_create_client() {
        ClientSdkDagger connectorImpl = new ClientSdkDagger();
        assertTrue(connectorImpl.createClient("http://localhost:8080") instanceof Client);
    }

    @Test
    public void test_should_throw_url_exception() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ClientSdkDagger connectorImpl = new ClientSdkDagger();
            connectorImpl.createRequestHttpGet("query{}");
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "URL is not initialized";
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
