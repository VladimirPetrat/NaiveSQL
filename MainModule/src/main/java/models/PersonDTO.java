package models;

import java.util.HashMap;
import java.util.Optional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonDTO {
    
    private HashMap<String, String> fields = new HashMap<>();
    private String errorArg = "ERROR! [Trying to get empty arguments]";
    private String errorTbl = "ERROR! [Trying to reach an empty table]";

    public PersonDTO(){}

    public PersonDTO(String id, String name, String year) {
        this.fields.put("id", id);
        this.fields.put("name", name);
        this.fields.put("year", year);
    }

    private String validateArg(String field, String errorMessage) {
        return Optional.ofNullable(field).orElseThrow(() -> new IllegalArgumentException(errorMessage));
    }

    private HashMap<String, String> validateTbl(HashMap<String, String> table, String errorMessage) {
        return Optional.ofNullable(table).orElseThrow(() -> new IllegalAccessError(errorMessage));
    }

    public HashMap<String, String> returnPersonDTO() {
        validateTbl(fields, errorTbl);

        return fields;
    }

    public void addPersonDTO(HashMap<String, String> newFields) {
        validateTbl(newFields, errorTbl);

        fields.putAll(newFields);
    }

    public void addField(String field, String fieldContent) {
        validateArg(field, errorArg);
        validateArg(fieldContent, errorArg);

        this.fields.put(field, fieldContent);
    }

    public void removePersonDTO() {
        validateTbl(fields, errorTbl);

        fields.clear();
    }

    public void removeField(String field) {
        validateArg(field, errorArg);

        if(!fields.get(field).isEmpty()) {
            fields.remove(field);
        }
        else {
            throw new IllegalAccessError("ERROR! [Trying to remove a field that doesn't exist]");
        }
    }

    public String returnField(String field) {
        validateArg(field, errorArg);

        return fields.get(field);
    }

    public void replaceField(String field, String newContent) {
        validateArg(field, errorArg);
        validateArg(newContent, errorArg);

        for(String key : fields.keySet()) {
            if(key == field) {
                fields.replace(field, newContent);
            }
        }
    }

    public boolean fieldExists(String field) {
        validateArg(field, errorArg);

        for(String key : fields.keySet()) {
            if(key == field) {
                return true;
            }
        }

        return false;
    }

    public boolean fieldIsEmpty(String field) {
        validateArg(field, errorArg);

        for(String key : fields.keySet()) {
            if(key == field) {
                if(fields.get(key).isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }
}