package com.jdagger.dto;

public class ExecCommand {
    private final String command;

    private String options = "";

    private boolean hasStdout = false;

    public ExecCommand(String command, String options, boolean hasStdout) {
        this.command = command;
        this.options = options;
        this.hasStdout = hasStdout;
    }

    public ExecCommand(String command){
        this.command = command;
    }

    public ExecCommand(String command, String options){
        this.command = command;
        this.options = options;
    }
    
    public ExecCommand(){
        this.command = null;
        this.options = null;
        this.hasStdout = false;
    }

    public String getCommand() {
        return command;
    }

    public String getOptions() {
        return options;
    }

    public boolean isHasStdout() {
        return hasStdout;
    }
}
