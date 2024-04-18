package com.jdagger;

import org.apache.http.HttpResponse;

import com.jdagger.builder.QueryBuilder;
import com.jdagger.builder.QueryBuilderImpl;
import com.jdagger.dto.Query;
import com.jdagger.exception.ContainerException;

public class App {
    public static void main(String[] args) throws ContainerException {
        QueryBuilder queryBuilder = new QueryBuilderImpl();
        Query query = queryBuilder
                .container()
                .from("openjdk:11")
                .withExec("ls", "-l")
                .build();

        CreateGraphqlQuery createGraphqlQuery = new CreateGraphqlQueryImpl();
        String graphqlQuery = createGraphqlQuery.create(query);

        ClientSdkDagger clientSdkDagger = new ClientSdkDagger();
        HttpResponse httpResponse = clientSdkDagger
                .createClient(String.format("http://127.0.0.1:8080/query"))
                .withToken("c052cdf4-80c6-4313-ba2c-533f2c03ea4c")
                .createRequestHttpGet(graphqlQuery)
                .execute();
                System.out.println(httpResponse);
    }
}
