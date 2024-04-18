package com.jdagger;

import org.apache.http.HttpResponse;

public interface Client {
    Client createClient(String url);

    Client createRequestHttpGet(String queryString);

    Client withToken(String token);

    HttpResponse execute();
}
