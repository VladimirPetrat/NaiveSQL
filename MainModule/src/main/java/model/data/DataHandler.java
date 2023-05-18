package model.data;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;

@AllArgsConstructor
public class DataHandler {
    //TODO add functionality regarding data operating
    private HashMap<String, Table> tables;

    public void createNewTable(String tableName, HashSet<String> columnNames) {
        tables.put(tableName, new Table(columnNames));
    }

    public <T> void insertIntoTable(String tableName, DataStructure<T> rowDataPackage) {
        var table = tables.get(tableName);
        table.addRow(rowDataPackage);
    }
}
