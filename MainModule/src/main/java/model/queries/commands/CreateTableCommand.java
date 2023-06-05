package model.queries.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.data.DataHandler;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;


@AllArgsConstructor
public class CreateTableCommand extends AbstractCommand implements IQueryCommand {
    
    @Override
    public void executeCommand(String commandLine) {
        if (commandLine.endsWith(";")) {
            commandLine = commandLine.substring(0, commandLine.length() - 1);
        }
        
        String[] commandArr = commandLine.split("\\s+");
        String commandType = commandArr[0];
        String tableName = commandArr[2];
        
        //TODO: fix it
        HashSet<String> columns = new HashSet<>(Arrays.asList(commandArr[3].split(",")));
        dataHandler.createNewTable(tableName, columns);
    }
    
    @Test
    public void testExecuteCommand() {
        DataHandler dataHandler = new DataHandler();
        CreateTableCommand command = new CreateTableCommand();
        
        String createTableCommand = "CREATE TABLE customers (id INT PRIMARY KEY, name VARCHAR(100) NOT NULL, age VARCHAR(255) UNIQUE);";
        
        command.executeCommand(createTableCommand);
        
        assertTrue(dataHandler.tableExists("customers"));
    }
}
