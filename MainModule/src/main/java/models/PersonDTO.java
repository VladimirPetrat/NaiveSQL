package models;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDTO {
    /**
     * Fields
     */
    private HashMap<String, String> fields;

    /**
     * Constructor
     */
    public PersonDTO(String id, String name, String year) {
        this.fields = new HashMap<>();
        this.fields.put("id", id);
        this.fields.put("name", name);
        this.fields.put("year", year);
    }

    /**
     * Returns the person's information as a map of key-value pairs.
     *
     * @return a map of key-value pairs representing the person's information
     */
    public HashMap<String, String> getFields() {
        return fields;
    }

    /**
     * Prints the person's information to the console.
     */
    public void printPerson() {
        HashMap<String, String> fields = getFields();
        for (String key : fields.keySet()) {
            System.out.println(key + ": " + fields.get(key));
        }
    }
}