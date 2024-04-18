package com.jdagger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

public class ClientSdkDagger implements Client {

    private String url = null;

    private HttpClient httpClient;

    private HttpGet httpGet;

    private String authToken;

    @Override
    public Client createClient(String url) {
        this.url = url;
        
        this.httpClient = HttpClientBuilder.create().build();

        return this;
    }

    public Client withToken(String token) {
        this.authToken = Base64.getEncoder().encodeToString((token + ":").getBytes(StandardCharsets.UTF_8));

        return this;
    }

    public Client createRequestHttpGet(String queryString) {
        if(this.url == null) throw new RuntimeException("URL is not initialized");
        HttpGet httpGet = new HttpGet(this.url);
        URI uri;
        try {
            uri = new URIBuilder(httpGet.getURI())
            .addParameter("query", queryString)
            .build();

            httpGet.setURI(uri);
            httpGet.addHeader("Authorization", "Basic "+this.authToken);

            this.httpGet = httpGet;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return this;
    }

    public HttpResponse execute(){
        try {
            return this.httpClient.execute(this.httpGet);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
