package model.queries.commands;

import lombok.AllArgsConstructor;
import model.data.DataHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


@AllArgsConstructor
public class CreateTableCommand extends AbstractCommand implements IQueryCommand {
    @Override
    public void executeCommand(String commandLine) {
        String[] commandArr = commandLine
                .replace("\\);", "")
                .split(" \\(");
        String tableName = commandArr[0];
        HashSet<String> columnNames = new HashSet<>(Arrays.asList(commandArr[1].split(",")));
        HashSet<String> temp = new HashSet<>();
        dataHandler.createNewTable(tableName, temp);
    }
}
