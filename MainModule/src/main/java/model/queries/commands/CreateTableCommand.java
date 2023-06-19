package model.queries.commands;

import lombok.AllArgsConstructor;
import model.data.DataHandler;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;


@AllArgsConstructor
public class CreateTableCommand extends AbstractCommand implements IQueryCommand {

    public DataHandler executeCommand(String commandLine) {
        DataHandler dataHandler = new DataHandler();

        if (commandLine.endsWith(";")) {
            commandLine = commandLine.substring(0, commandLine.length() - 1);
        }

        String[] commandArr = commandLine.split("\\s+");
        String tableName = commandArr[2];

        HashSet<String> columns = new HashSet<>(Arrays.asList(commandArr[3].split(",")));
        dataHandler.createNewTable(tableName, columns);

        return dataHandler;
    }

    @Test
    public void testExecuteCommand() {
        CreateTableCommand command = new CreateTableCommand();

        String createTableCommand = "CREATE TABLE customers (id INT PRIMARY KEY, name VARCHAR(100) NOT NULL, age VARCHAR(255) UNIQUE);";

        DataHandler dataHandler = command.executeCommand(createTableCommand);

        assertTrue(dataHandler.tableExists("customers"));
    }
}
