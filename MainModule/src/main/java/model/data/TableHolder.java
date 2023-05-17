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

    public String addRow(HashMap<String, String> rowDataPackage) {
        if (!verifyColumnNamesCorrect(rowDataPackage)) {
            throw new IllegalArgumentException("[ERROR] Incorrect rows in insert data package");
        }
        rows.put(generateUniqueId(), rowDataPackage);

        return returnUniqueId();
    }

    public void removeRow(String id) throws IllegalAccessException {
        if (rows.isEmpty()) {
            throw new IllegalAccessException("[ERROR] Trying to remove an empty row");
        }
        rows.remove(verifyId(id));
    }

    public void replaceRow(String id, HashMap<String, String> rowDataPackage) {
        if (!verifyColumnNamesCorrect(rowDataPackage)) {
            throw new IllegalArgumentException("[ERROR] Incorrect rows in insert data package");
        }
        rows.replace(verifyId(id), rowDataPackage);
    }

    private String verifyId(String id) {
        if (!rows.containsKey(id)) {
            throw new IllegalArgumentException("[ERROR] Incorrect ID");
        }

        return id;
    }

    private boolean verifyColumnNamesCorrect(HashMap<String, String> rowDataPackage) {
        if (rowDataPackage.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] Trying to get an empty argument");
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

    private String returnUniqueId() {
        return rows.keySet()
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR Such an id doesn't exist]"));
    }
}
