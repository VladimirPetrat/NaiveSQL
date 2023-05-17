package model.data;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

@Data
public class TableHolder {

    private HashSet<String> columnNames;
    private HashMap<String, Map<String, String>> rows;

    public TableHolder(HashSet<String> columnNames) {
        this.columnNames = columnNames;
        rows = new HashMap<>();
    }

    public void addRow(HashMap<String, String> rowDataPackage) {
        if (!verifyColumnNamesCorrect(rowDataPackage)) {
            throw new IllegalArgumentException("Incorrect rows in insert data package");
        }
        rows.put(generateUniqueId(), rowDataPackage);
    }

    private boolean verifyColumnNamesCorrect(HashMap<String, String> rowDataPackage) {
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
