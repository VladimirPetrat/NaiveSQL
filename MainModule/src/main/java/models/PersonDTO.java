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

    public void replaceID(String newId){
        for(String key : fields.keySet()){
            if(key == "id"){
                fields.replace("id", newId);
            }
        }
    }
}