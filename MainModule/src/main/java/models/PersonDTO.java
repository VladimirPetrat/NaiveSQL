package models;

import java.util.HashMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonDTO {
    
    private HashMap<String, String> fields = new HashMap<>();

    public PersonDTO(){}

    public PersonDTO(String id, String name, String year) {
        this.fields.put("id", id);
        this.fields.put("name", name);
        this.fields.put("year", year);
    }

    public HashMap<String, String> returnPersonDTO() {
        if(!fields.isEmpty()) {
            return fields;
        }
        else {
            throw new IllegalAccessError("ERROR! [Trying to output an empty class object]");
        }
    }

    public void addPersonDTO(HashMap<String, String> newFields) {
        if(!newFields.isEmpty()) {
            this.fields.putAll(newFields);
        }
        else {
            throw new IllegalArgumentException("ERROR! [Trying to get empty arguments]");
        }
    }

    public void addField(String field, String fieldContent) {
        if(!field.isEmpty() && !fieldContent.isEmpty()) {
            this.fields.put(field, fieldContent);
        }
        else {
            throw new IllegalArgumentException("ERROR! [Trying to get empty arguments]");
        }
    }

    public void removePersonDTO() {
        if(!fields.isEmpty()) {
            fields.clear();
        }
        else {
            throw new IllegalAccessError("ERROR! [Trying to clean an empty HashTable]");
        }
    }

    public void removeField(String field) {
        if(!field.isEmpty() && !fields.get(field).isEmpty()) {
            fields.remove(field);
        }
        else {
            throw new IllegalArgumentException("ERROR! [Trying to remove a field from an empty HashTable or the field doesn't exist]");
        }
    }

    public String returnField(String field) {
        return fields.get(field);
    }

    public void replaceField(String field, String newContent) {
        for(String key : fields.keySet()) {
            if(key == field) {
                fields.replace(field, newContent);
            }
        }
    }

    public boolean fieldExists(String field) {
        for(String key : fields.keySet()) {
            if(key == field) {
                return true;
            }
        }

        return false;
    }

    public boolean fieldIsEmpty(String field) {
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