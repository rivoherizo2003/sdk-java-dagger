package com.jdagger.builder;

import com.jdagger.dto.Query;
import com.jdagger.exception.ContainerException;

public interface QueryBuilder {
    QueryBuilder container();

    QueryBuilder from(String tagImage) throws ContainerException;

    QueryBuilder withExec(String command, String options);

    Query build();
}
