package models;

import java.util.List;
import java.util.ArrayList;

public class Person {

    /**
     * A list of Strings to hold the person's information.
     */
    private List<String> fields;

    /**
     * fields
     */
    String id, name, year;

    /**
     * Constructs a Person object with the given id, name, and year information.
     * @param id the person's id
     * @param name the person's name
     * @param year the year the person was born
     */
    public Person(String id, String name, String year){
        this.fields = null;
        this.id = id;
        this.name = name;
        this.year = year;
    }

    /**
     * Prints the person's information.
     */
    public void printPerson() {
        if (this.fields == null) {
            this.fields = new ArrayList<String>();
            this.fields.add(this.id);
            this.fields.add(this.name);
            this.fields.add(this.year);
        }
        for (String field : this.fields) {
            System.out.println(field);
        }
    }
}

