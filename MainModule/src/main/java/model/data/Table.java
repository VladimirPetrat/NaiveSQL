package model.data;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class Table {
	
	private DataHandler dataHandler;
	
	private final String errorId = "[ERROR] [Incorrect ID]";
	private final String errorArg = "[ERROR] [Incorrect rows in insert data package]";
	
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
	
	public List<String> addNewRows(List<List<DataObject>> dataPackages) {
		List<String> ids = new ArrayList<>();
		
		for(List<DataObject> dataPackage : dataPackages) {
			ids.add(addNewRow(dataPackage));
		}
		
		return ids;
	}
	
	public void addNewColumn(String columnName) {
		verifyColumnNameCorrect(columnName);
		
		columnNames.add(columnName);
		
		//TODO Possible refactor due to DataObject updates
		rows.forEach((key, value) -> value.addField(new DataObject(columnName, new Object(), Object.class)));
	}
	
	public DataObject getFieldValueObject(String Id, String fieldName) {
		verifyIdAndField(Id, fieldName);
		
		return rows.get(Id).getFieldDataObject(fieldName);
	}
	
	public List<DataObject> getFieldsForId(String id) {
		verifyField(id);
		
		return rows.get(id).getFields();
	}
	
	public List<DataObject> getFieldsForColumnName(String columnName) {
		verifyField(columnName);
		
		return rows.values().stream().map(value -> value.getFieldDataObject(columnName)).collect(Collectors.toList());
	}
	
	public void insertRowFieldValues(String id, List<DataObject> dataPackage) {
		verifyIdAndColumns(id, dataPackage);
		
		dataPackage.forEach(dataObject -> insertRowFieldValue(id, dataObject));
	}
	
	public void insertRowFieldValue(String id, DataObject dataObject) {
		verifyId(id);
		
		rows.get(id).insertField(dataObject);
	}
	
	public void removeRow(String id) {
		verifyId(id);
		
		rows.remove(id);
	}
	
	public void removeRows(HashSet<String> ids) {
		verifyMultIds(ids);
		
		ids.forEach(this::removeRow);
	}
	
	public void removeRowValue(String id, String fieldName) {
		verifyIdAndField(id, fieldName);
		
		rows.get(id).removeFieldValue(fieldName);
	}
	
	public void removeColumn(String columnName) {
		verifyColumnNameCorrect(columnName);
		
		rows.values().forEach(row -> row.removeField(columnName));
	}
	
	public void removeColumns(HashSet<String> columnNames) {
		columnNames.forEach(this::removeColumn);
	}
	
	public void removeColumnValues(String columnName) {
		verifyColumnNameCorrect(columnName);
		
		rows.values().forEach(row -> row.removeFieldValue(columnName));
	}
	
	public void removeColumnsValues(HashSet<String> columnNames) {
		verifyMultColumns(columnNames);
		
		columnNames.forEach(this::removeColumnValues);
	}
	
	
	private void verifyField(String arg) {
		if(arg.isEmpty()) {
			throw new IllegalArgumentException(errorArg);
		}
	}
	
	private void verifyId(String id) {
		if(!rows.containsKey(id)) {
			throw new IllegalArgumentException(errorId);
		}
	}
	
	private void verifyMultIds(HashSet<String> ids) {
		if(ids.isEmpty() || !rows.keySet().containsAll(ids)) {
			throw new IllegalArgumentException(errorId);
		}
	}
	
	private void verifyColumnNameCorrect(String columnName) {
		if(!columnNames.contains(columnName)) {
			throw new IllegalArgumentException(errorArg);
		}
	}
	
	private void verifyColumnNamesCorrect(List<DataObject> dataPackage) {
		Set<String> fieldNames = dataPackage.stream().map(DataObject::getFieldName).collect(Collectors.toSet());
		if(dataPackage.isEmpty() || !columnNames.containsAll(fieldNames)) {
			throw new IllegalArgumentException(errorArg);
		}
	}
	
	private void verifyMultColumns(HashSet<String> columnNames) {
		if(columnNames.isEmpty() || !columnNames.containsAll(columnNames)) {
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
		} while(this.rows.containsKey(uniqueId));
		return uniqueId;
	}
	
}
