package model.queries.commands;

import lombok.AllArgsConstructor;
import model.data.DataHandler;

public abstract class AbstractCommand {

    public DataHandler dataHandler = new DataHandler();
}
