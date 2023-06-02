package model.queries.commands;

import lombok.AllArgsConstructor;
import model.data.DataHandler;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;


@AllArgsConstructor
public class CreateTableCommand extends AbstractCommand implements IQueryCommand {
    
    @Override
    public void executeCommand(String commandLine) {
        String[] commandArr = commandLine
                .replace("\\);", "")
                .split(" \\(");
        String tableName = commandArr[0];
        HashSet<String> columnNames = new HashSet<>(Arrays.asList(commandArr[1].split(",")));
        dataHandler.createNewTable(tableName, columnNames);
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
