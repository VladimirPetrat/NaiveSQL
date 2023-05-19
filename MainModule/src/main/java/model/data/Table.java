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
        verifyIdAndField(Id, fieldName);

        return rows
                .get(Id)
                .stream()
                .filter(dataStructure -> dataStructure.getFieldName().equals(fieldName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Missing field name"))
                .getValue();
    }

    public DataObject getDataForFieldName(String Id, String fieldName) {
        verifyIdAndField(Id, fieldName);

        return rows
                .get(Id)
                .stream()
                .filter(dataStructure -> dataStructure.getFieldName().equals(fieldName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Missing field name"));
    }

    public String addRow(List<DataObject> dataPackage) {
        verifyColumnNamesCorrect(dataPackage);

        String id = generateUniqueId();
        rows.put(id, dataPackage);

        return id;
    }

    public void removeRow(String id) {
        verifyId(id);
        verifyRows(rows, errorRws);

        rows.remove(id);
    }

    public void updateRowFieldValues(String id, List<DataObject> dataPackage) {
        verifyIdAndColumns(id, dataPackage);

        List<DataObject> existingRow = rows.get(id);
        for (DataObject newDataObject : dataPackage) {
            String fieldName = newDataObject.getFieldName();
            Optional<DataObject> existingDataObject = existingRow.stream()
                    .filter(dataObject -> dataObject.getFieldName().equals(fieldName))
                    .findFirst();
            if (existingDataObject.isPresent()) {
                DataObject updatedDataObject = existingDataObject.get();
                updatedDataObject.setValue(newDataObject.getValue());
            } else {
                existingRow.add(newDataObject);
            }
        }
    }

    public boolean rowIsEmpty() {
        return rows.isEmpty();
    }

    private void verifyRows(HashMap<String, List<DataObject>> rows, String errorMessage) {
        Objects.requireNonNull(rows, "Rows cannot be null.");
        if (rows.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void verifyField(String arg) {
        if (arg.isEmpty()) {
            throw new IllegalArgumentException(errorArg);
        }
    }

    private void verifyId(String id) {
        if (!rows.containsKey(id)) {
            throw new IllegalArgumentException(errorId);
        }
    }

    private void verifyColumnNamesCorrect(List<DataObject> dataPackage) {
        List<String> fieldNames = dataPackage.stream()
                .map(DataObject::getFieldName)
                .toList();
        if (dataPackage.isEmpty() || !columnNames.containsAll(fieldNames)) {
            throw new IllegalArgumentException(errorArg);
        }
    }

    private void verifyIdAndField(String id, String field) {
        verifyId(id);
        verifyField(field);
    }

    private void verifyIdAndColumns(String id, List<DataObject> dataPackage) {
        verifyId(id);
        verifyColumnNamesCorrect(dataPackage);
    }

    private String generateUniqueId() {
        String uniqueId;
        do {
            uniqueId = UUID.randomUUID().toString();
        } while (this.rows.containsKey(uniqueId));
        return uniqueId;
    }

}
