package models;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDTO {
    
    private HashMap<String, String> fields;

    public PersonDTO(String id, String name, String year) {
        this.fields = new HashMap<>();
        this.fields.put("id", id);
        this.fields.put("name", name);
        this.fields.put("year", year);
    }

    public HashMap<String, String> returnPersonDTO(){
        return fields;
    }

    private void operationReplace(String field, String newField){
        for(String key : fields.keySet()){
            if(key == field){
                fields.replace(field, newField);
            }
        }
    }

    public void replaceID(String newId){
        operationReplace("id", newId);
    }

    public void replaceName(String newName){
        operationReplace("name", newName);
    }

    public void replaceYear(String newYear){
        operationReplace("year", newYear);
    }
}