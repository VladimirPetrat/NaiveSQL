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
            throw new IllegalArgumentException("Invalid output! [Empty class object]");
        }
    }

    public void addPersonDTO(HashMap<String, String> newFields) {
        if(!newFields.isEmpty()) {
            this.fields.putAll(newFields);
        }
        else {
            throw new IllegalArgumentException("Invalid input! [Empty argument object]");
        }
    }

    public void addField(String field, String fieldContent) {
        this.fields.put(field, fieldContent);
    }

    public void removePersonDTO() {
        fields.clear();
    }

    public void removeField(String field) {
        fields.remove(field);
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