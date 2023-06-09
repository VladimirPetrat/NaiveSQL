package model.data;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class Table {

    private DataHandler dataHandler;

    private final String errorId = "[ERROR] [Incorrect ID]";
    private final String errorArg = "[ERROR] [Incorrect rows in insert data package]";
    private final String errorRws = "[ERROR] [Trying to reach an empty row]";

    @Getter
    private HashSet<String> columnNames;
    private HashMap<String, TableRow> rows;

    public Table(HashSet<String> columnNames) {
        this.columnNames = columnNames;
        rows = new HashMap<>();
    }

    public String addNewRow(List<DataObject> dataPackage) {
        verifyColumnNamesCorrect(dataPackage);

        String id = generateUniqueId();
        rows.put(id, new TableRow(dataPackage));

        return id;
    }

    public void addNewColumn(String columnName) {
        verifyColumnNameCorrect(columnName);

        columnNames.add(columnName);

        //TODO Possible refactor due to DataObject updates
        rows.forEach((key, value) -> value.addField(new DataObject(columnName, new Object(), Object.class)));
    }

    public DataObject getFieldValueObject(String Id, String fieldName) {
        verifyIdAndField(Id, fieldName);

        return rows
                .get(Id)
                .getFieldDataObject(fieldName);
    }

    public List<DataObject> getFieldsForId(String id) {
        verifyField(id);

        return rows
                .get(id)
                .getFields();
    }

    public List<DataObject> getFieldsForColumnName(String columnName) {
        return rows
                .values()
                .stream()
                .map(value -> value.getFieldDataObject(columnName))
                .collect(Collectors.toList());
    }

    public void insertRowFieldValues(String id, List<DataObject> dataPackage) {
        dataPackage.forEach(dataObject -> insertRowFieldValue(id, dataObject));
    }

    public void insertRowFieldValue(String id, DataObject dataObject) {
        verifyId(id);
        verifyColumnNameCorrect(dataObject.getFieldName());

        rows.get(id).insertField(dataObject);
    }

    public void removeRow(String id) {
        verifyId(id);

        rows.remove(id);
    }

    public void removeRows(HashSet<String> ids) {
        ids.forEach(this::removeRow);
    }

    public void removeRowValue(String id, String fieldName) {
        verifyColumnNameAndIdCorrect(id, fieldName);
        rows.get(id).removeFieldValue(fieldName);
    }

    public void removeColumn(String columnName) {
        verifyColumnNameCorrect(columnName);

        rows
                .values()
                .forEach(row -> row.removeField(columnName));
    }

    public void removeColumns(HashSet<String> columnNames) {
        columnNames.forEach(this::removeColumn);
    }

    public void removeColumnValues(String columnName) {
        verifyColumnNameCorrect(columnName);

        rows
                .values()
                .forEach(row -> row.removeFieldValue(columnName));
    }

    public void removeColumnsValues(HashSet<String> columnNames) {
        columnNames.forEach(this::removeColumnValues);
    }

    private void verifyColumnNameAndIdCorrect(String columnName, String id) {
        verifyColumnNameCorrect(columnName);
        verifyId(id);
    }

    private void verifyRows(HashMap<String, TableRow> rows) {
        Objects.requireNonNull(rows, "Rows cannot be null.");
        if (rows.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] [Trying to reach an empty row]");
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

    private void verifyColumnNameCorrect(String columnName) {
        if (!columnNames.contains(columnName)) {
            throw new IllegalArgumentException(errorArg);
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
