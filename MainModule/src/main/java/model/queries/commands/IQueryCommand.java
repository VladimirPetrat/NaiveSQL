package model.queries.commands;

import model.data.DataHandler;

import javax.xml.crypto.Data;

public interface IQueryCommand {

    DataHandler executeCommand(String commandLine);
}
