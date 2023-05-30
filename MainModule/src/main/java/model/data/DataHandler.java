package model.data;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
public class DataHandler {
    //TODO add functionality regarding data operating
    private HashMap<String, Table> tables;

    //TODO Add proper table
    public DataHandler() {
        tables = new HashMap<>();
    }

    public Table createNewTable(String tableName, HashSet<String> columnNames) {
        var table = new Table(columnNames);
        tables.put(tableName, new Table(columnNames));
        return table;
    }

    public void insertIntoTable(String tableName, List<DataObject> objects) {
        var table = tables.get(tableName);
//        table.addRow(rowDataPackage);
    }
}
