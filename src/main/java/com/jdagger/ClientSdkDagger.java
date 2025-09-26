package com.jdagger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.net.URIBuilder;

public class ClientSdkDagger implements Client {

    private String url = null;

    private CloseableHttpClient httpClient;

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
        if (this.url == null)
            throw new RuntimeException("URL is not initialized");
        HttpGet httpGet = new HttpGet(this.url);
        URI uri;
        try {
            uri = new URIBuilder(httpGet.getUri())
                    .addParameter("query", queryString)
                    .build();

            httpGet.setUri(uri);
            httpGet.addHeader("Authorization", "Basic " + this.authToken);

            this.httpGet = httpGet;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return this;
    }

    public void execute() {
        try {
            System.out.println(this.httpClient.executeOpen(null, this.httpGet, null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
