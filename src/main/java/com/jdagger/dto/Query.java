package com.jdagger.dto;

import java.util.HashSet;

public class Query {
    
    public boolean containerInitialized = false;
    
    public String from = null;

    public HashSet<ExecCommand> commandExec = new HashSet<ExecCommand>();
}
