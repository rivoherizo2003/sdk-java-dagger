package com.jdagger;

public interface Client {
    Client createClient(String url);

    Client createRequestHttpGet(String queryString);

    Client withToken(String token);

    void execute();
}
