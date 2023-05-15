package model.data;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Data
public class TableHolder {

    private HashSet<String> columnNames;
    private List<HashMap<String, String>> rows;

    public TableHolder(HashSet<String> columnNames) {
        this.columnNames = columnNames;
        HashMap<String, String> firstEmptyRow = new HashMap<>();
        columnNames.forEach(columnName -> firstEmptyRow.put(columnName, ""));
        rows.add(firstEmptyRow);
    }

    public void addRow(HashMap<String, String> rowDataPackage) {
        if (!verifyColumnNamesCorrect(rowDataPackage)) {
            //TODO Create respectful exception schema
            throw new RuntimeException("Incorrect rows in insert data package");
        }

        rows.add(rowDataPackage);
    }

    private boolean verifyColumnNamesCorrect(HashMap<String, String> rowDataPackage) {
        return columnNames.containsAll(rowDataPackage.keySet());
    }
}
