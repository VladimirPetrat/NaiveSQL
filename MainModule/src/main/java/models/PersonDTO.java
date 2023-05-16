package models;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonDTO {
    
    private HashMap<String, String> fields;

    public PersonDTO(String id, String name, String year) {
        this.fields = new HashMap<>();
        this.fields.put("id", id);
        this.fields.put("name", name);
        this.fields.put("year", year);
    }

    public HashMap<String, String> returnPersonDTO() {
        return fields;
    }

    public void addField(String field, String fieldContent) {
        this.fields.put(field, fieldContent);
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
}