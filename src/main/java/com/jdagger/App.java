package com.jdagger;

import com.jdagger.builder.QueryBuilder;
import com.jdagger.builder.QueryBuilderImpl;
import com.jdagger.dto.Query;
import com.jdagger.exception.ContainerException;

public class App {
    public static void main(String[] args) throws ContainerException {
        System.out.println("BONOUR");
        QueryBuilder queryBuilder = new QueryBuilderImpl();
        Query query = queryBuilder
                .container()
                .from("openjdk:11")
                .withExec("java", "--version")
                .build();

        CreateGraphqlQuery createGraphqlQuery = new CreateGraphqlQueryImpl();
        String graphqlQuery = createGraphqlQuery.create(query);

        ClientSdkDagger clientSdkDagger = new ClientSdkDagger();
        clientSdkDagger
                .createClient(String.format("http://127.0.0.1:{}/query"))
                .withToken("081b3db6-1102-4088-843e-758fdccfbe52")
                .createRequestHttpGet(graphqlQuery)
                .execute();

    }
}
