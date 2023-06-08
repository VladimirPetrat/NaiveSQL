package model.queries.commands;

import model.data.DataHandler;

public class AddRowToTableCommand extends AbstractCommand implements IQueryCommand {
	@Override
	public DataHandler executeCommand(String commandLine) {
		//TODO Add logic for respectful sql query :
		//INSERT INTO table_name (column1, column2, column3, ...)
		//VALUES (value1, value2, value3, ...);
		
		String[] commandArr = commandLine
				.replace("\\);", "")
				.split(" \\(");
		
		return null;
	}
}
