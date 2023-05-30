package model.queries.commands;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;


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
}
