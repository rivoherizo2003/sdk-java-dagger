package com.jdagger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jdagger.builder.QueryBuilder;
import com.jdagger.builder.QueryBuilderImpl;
import com.jdagger.dto.Query;
import com.jdagger.exception.ContainerException;

public class CreateGraphqlQueryTest {
    private String buildGraphQlQueryForOpenJDK11() throws ContainerException {
        QueryBuilder queryBuilder = new QueryBuilderImpl();
        Query query = queryBuilder
                .container()
                .from("openjdk:11")
                .withExec("ls", "-l")
                .build();

        CreateGraphqlQuery createGraphqlQuery = new CreateGraphqlQueryImpl();
        return createGraphqlQuery.create(query);
    }

    @Test
    public void should_return_string_graphql_query() throws ContainerException {
        String expectedGraphqlQuery = "query{container{from(address:\"openjdk:11\"){withExec(args:[\"ls\",\"-l\"]){stdout}}}}";
        String graphqlQuery = this.buildGraphQlQueryForOpenJDK11();
        assertTrue(graphqlQuery instanceof String);
        assertEquals(graphqlQuery.replaceAll("[\n\t\s]", ""), expectedGraphqlQuery);
    }

    public void should_execute_graphql_query() throws ContainerException {

    }
}
