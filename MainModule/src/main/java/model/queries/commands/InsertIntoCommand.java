package model.queries.commands;

import lombok.AllArgsConstructor;
import model.data.DataHandler;

@AllArgsConstructor
public class InsertIntoCommand extends AbstractCommand implements IQueryCommand {
    @Override
    public DataHandler executeCommand(String commandLine) {
        return null;
    }
}
