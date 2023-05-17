package model.data;

import lombok.Data;

import java.util.*;

@Data
public class TableHolder {

    private String errorId = "[ERROR] [Incorrect ID]";
    private String errorArg = "[ERROR] [Incorrect rows in insert data package]";
    private String errorRws = "[ERROR] [Trying to reach an empty row]";
    private HashSet<String> columnNames;
    private HashMap<String, Map<String, String>> rows;

    public TableHolder(HashSet<String> columnNames) {
        this.columnNames = columnNames;
        rows = new HashMap<>();
    }

    public String addRow(HashMap<String, String> rowDataPackage) {
        if (!verifyColumnNamesCorrect(rowDataPackage)) {
            throw new IllegalArgumentException(errorArg);
        }

        String id = generateUniqueId();
        rows.put(id, rowDataPackage);

        return id;
    }

    public void removeRow(String id){
        verifyRows(rows, errorRws);
        rows.remove(verifyId(id));
    }

    public void replaceRow(String id, HashMap<String, String> rowDataPackage) {
        if (!verifyColumnNamesCorrect(rowDataPackage)) {
            throw new IllegalArgumentException(errorArg);
        }
        rows.replace(verifyId(id), rowDataPackage);
    }

    public boolean rowIsEmpty() {
        return rows.isEmpty();
    }

    private HashMap<String, Map<String, String>> verifyRows(HashMap<String, Map<String, String>> rows, String errorMessage) {
        return Optional.ofNullable(rows)
                .orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    private String verifyId(String id) {
        if (!rows.containsKey(id)) {
            throw new IllegalArgumentException(errorId);
        }

        return id;
    }

    private boolean verifyColumnNamesCorrect(HashMap<String, String> rowDataPackage) {
        if (rowDataPackage.isEmpty()) {
            throw new IllegalArgumentException(errorArg);
        }
        return columnNames.containsAll(rowDataPackage.keySet());
    }

    private String generateUniqueId() {
        String uniqueId;
        do {
            uniqueId = UUID.randomUUID().toString();
        } while (this.rows.containsKey(uniqueId));
        return uniqueId;
    }

}
