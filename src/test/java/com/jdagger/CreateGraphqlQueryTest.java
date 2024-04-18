package com.jdagger;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jdagger.builder.QueryBuilder;
import com.jdagger.builder.QueryBuilderImpl;
import com.jdagger.dto.Query;
import com.jdagger.exception.ContainerException;

public class CreateGraphqlQueryTest {
    @Test
    public void should_return_string_graphql_query() throws ContainerException{
        String expectedGraphqlQuery = "query{container{from(address:\"openjdk:11\"){withExec(args:[\"ls\",\"-l\"]){stdout}}}}";
        QueryBuilder queryBuilder = new QueryBuilderImpl();
        Query query = queryBuilder
            .container()
            .from("openjdk:11")
            .withExec("ls", "-l")
            .build();
        
        CreateGraphqlQuery createGraphqlQuery = new CreateGraphqlQueryImpl();
        String graphqlQuery = createGraphqlQuery.create(query);

        System.out.println(graphqlQuery.replaceAll("[\n\t\s]", ""));
        assertTrue(graphqlQuery instanceof String);
        assertEquals(graphqlQuery.replaceAll("[\n\t\s]", ""), expectedGraphqlQuery);
    }
}
