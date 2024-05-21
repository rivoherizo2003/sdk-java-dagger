package com.jdagger;

import com.jdagger.dto.ExecCommand;
import com.jdagger.dto.Query;

public class CreateGraphqlQueryImpl implements CreateGraphqlQuery{
    private StringBuilder stringBuilder = new StringBuilder();

	public String create(Query query) {
		//test query IS NOT NULL
		
		
		this.stringBuilder.append("query {\n");

		if(query.containerInitialized) {
			this.stringBuilder.append("\tcontainer {\n");
            this.stringBuilder.append("\t\tfrom(address: \"")
			.append(query.from)
			.append("\") {\n");

            for(ExecCommand execCommand : query.commandExec) {
                this.stringBuilder.append("\t\t\twithExec(args: [\"")
				.append(execCommand.getCommand())
				.append("\", \"")
				.append(execCommand.getOptions()).append("\"]) {\n");
                this.stringBuilder.append("\t\t\t\tstdout\n");
                this.stringBuilder.append("\t\t\t}\n");
            }

            this.stringBuilder.append("\t\t}\n");
            this.stringBuilder.append("\t}\n");
		}

		this.stringBuilder.append("}");
	
		return stringBuilder.toString();
	}
}
