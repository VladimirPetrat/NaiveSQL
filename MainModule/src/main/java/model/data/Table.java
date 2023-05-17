package model.data;

import lombok.Data;

import java.util.*;

@Data
public class Table<T> {

    private String errorId = "[ERROR] [Incorrect ID]";
    private String errorArg = "[ERROR] [Incorrect rows in insert data package]";
    private String errorRws = "[ERROR] [Trying to reach an empty row]";
    private HashSet<String> columnNames;
    private HashMap<String, Map<String, T>> rows;

    public Table(HashSet<String> columnNames) {
        this.columnNames = columnNames;
        rows = new HashMap<>();
    }

    public String addRow(HashMap<String, T> rowDataPackage) {
        verifyColumnNamesCorrect(rowDataPackage);

        String id = generateUniqueId();
        rows.put(id, rowDataPackage);

        return id;
    }

    public void removeRow(String id) {
        verifyId(id);
        verifyRows(rows, errorRws);
        rows.remove(id);
    }

    public void updateRowFieldValues(String id, HashMap<String, T> rowDataPackage) {
        verifyId(id);
        verifyColumnNamesCorrect(rowDataPackage);
        rows.get(id).putAll(rowDataPackage);
    }

    public void replaceRowFieldValues(String id, HashMap<String, T> rowDataPackage) {
        verifyId(id);
        verifyColumnNamesCorrect(rowDataPackage);
        var existingRow = rows.get(id);
        existingRow
                .keySet()
                .forEach(field -> existingRow.put(field, rowDataPackage.getOrDefault(field, null)));
    }

    public boolean rowIsEmpty() {
        return rows.isEmpty();
    }

    private HashMap<String, Map<String, T>> verifyRows(HashMap<String, Map<String, T>> rows, String errorMessage) {
        return Optional
                .ofNullable(rows)
                .orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    private void verifyId(String id) {
        if (!rows.containsKey(id)) {
            throw new IllegalArgumentException(errorId);
        }
    }

    private void verifyColumnNamesCorrect(HashMap<String, T> rowDataPackage) {
        if (rowDataPackage.isEmpty() || !columnNames.containsAll(rowDataPackage.keySet())) {
            throw new IllegalArgumentException(errorArg);
        }
    }

    private String generateUniqueId() {
        String uniqueId;
        do {
            uniqueId = UUID.randomUUID().toString();
        } while (this.rows.containsKey(uniqueId));
        return uniqueId;
    }

}