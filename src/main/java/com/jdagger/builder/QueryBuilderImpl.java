package com.jdagger.builder;

import java.util.HashSet;

import com.jdagger.dto.ExecCommand;
import com.jdagger.dto.Query;
import com.jdagger.exception.ContainerException;

public class QueryBuilderImpl implements QueryBuilder{
    private boolean containerInitialized = false;
    private String from = null;

    private HashSet<ExecCommand> commandExec = new HashSet<ExecCommand>();

    @Override
    public QueryBuilder container() {
        this.containerInitialized = true;
        return this;
    }

    @Override
    public QueryBuilder from(String from) throws ContainerException {
        if(!containerInitialized) throw new ContainerException("Container is not initialized");
        this.from = from;

        return this;
    }

    @Override
    public QueryBuilder withExec(String command, String options) {
        commandExec.add(new ExecCommand(command, options));

        return this;
    }

    @Override
    public Query build() {
        Query query = new Query();
        query.from = this.from;
        query.commandExec = this.commandExec;
        query.containerInitialized = this.containerInitialized;

        return query;
    }
}
