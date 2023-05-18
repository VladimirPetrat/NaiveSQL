package model.data;

import lombok.Data;

import java.util.*;

@Data
public class Table {

    private String errorId = "[ERROR] [Incorrect ID]";
    private String errorArg = "[ERROR] [Incorrect rows in insert data package]";
    private String errorRws = "[ERROR] [Trying to reach an empty row]";
    private HashSet<String> columnNames;
    private HashMap<String, List<DataObject>> rows;

    public Table(HashSet<String> columnNames) {
        this.columnNames = columnNames;
        rows = new HashMap<>();
    }

    public Object getFieldValue(String Id, String fieldName) {
        return rows
                .get(Id)
                .stream()
                .filter(dataStructure -> dataStructure.getFieldName().equals(fieldName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Missing field name"))
                .getValue();
    }

    public DataObject getDataForFieldName(String Id, String fieldName) {
        return rows
                .get(Id)
                .stream()
                .filter(dataStructure -> dataStructure.getFieldName().equals(fieldName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Missing field name"));
    }

    public String addRow(List<DataObject> dataPackage) {
//        verifyColumnNamesCorrect(rowDataPackage);

        String id = generateUniqueId();
        rows.put(id, dataPackage);

        return id;
    }

//    public void removeRow(String id) {
//        verifyId(id);
//        verifyRows(rows, errorRws);
//
//        rows.remove(id);
//    }

//    public void updateRowFieldValues(String id, Map<String , String> rowDataPackage) {
//        verifyIdAndColumns(id, rowDataPackage);
//
//        rows.get(id).putAll(rowDataPackage);
//    }

//    public void replaceRowFieldValues(String id,Map<String , String> rowDataPackage) {
//        verifyIdAndColumns(id, rowDataPackage);
//
//        var existingRow = rows.get(id);
//        existingRow
//                .keySet()
//                .forEach(field -> existingRow.put(field, rowDataPackage.getOrDefault(field, null)));
//    }

    public boolean rowIsEmpty() {
        return rows.isEmpty();
    }

    private HashMap<String, DataObject> verifyRows(HashMap<String, DataObject> rows, String errorMessage) {
        return Optional
                .ofNullable(rows)
                .orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    private void verifyId(String id) {
        if (!rows.containsKey(id)) {
            throw new IllegalArgumentException(errorId);
        }
    }

//    private void verifyColumnNamesCorrect(Map<String , String> rowDataPackage) {
//        if (rowDataPackage.isEmpty() || !columnNames.containsAll(rowDataPackage.keySet())) {
//            throw new IllegalArgumentException(errorArg);
//        }
//    }

//    private void verifyIdAndColumns(String id, Map<String , String> rowDataPackage){
//        verifyId(id);
//        verifyColumnNamesCorrect(rowDataPackage);
//    }

    private String generateUniqueId() {
        String uniqueId;
        do {
            uniqueId = UUID.randomUUID().toString();
        } while (this.rows.containsKey(uniqueId));
        return uniqueId;
    }

}
