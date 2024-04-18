package com.jdagger.builder;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

import com.jdagger.dto.ExecCommand;
import com.jdagger.dto.Query;
import com.jdagger.exception.ContainerException;

public class QueryBuilderImplTest {
    @Test
    public void should_return_valid_query_object() throws ContainerException{
        QueryBuilder queryBuilder = new QueryBuilderImpl();
        Query query = queryBuilder
            .container()
            .from("openjdk:11")
            .withExec("ls", "-l")
            .build();
        
        assertTrue(query instanceof Query);

        assertTrue(query.containerInitialized);

        assertEquals(query.from, "openjdk:11");
        assertTrue( query.commandExec instanceof HashSet<ExecCommand>);
    }
}
