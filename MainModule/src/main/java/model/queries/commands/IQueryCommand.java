package model.queries.commands;

import model.data.DataHandler;

public interface IQueryCommand {
    DataHandler executeCommand(String commandLine);
}
