package models;

import java.util.List;
import java.util.ArrayList;

public class Person {

    //list of Strings for holding data
    private List<String> fields = new ArrayList<String>();

    //Constructor for adding fields into the list
    public Person(String id, String name, String year){
        fields.add(id);
        fields.add(name);
        fields.add(year);
    }

    //output function
    public void printPerson(){

        int i = 0;
        //loop for outputting info
        while(i < fields.size()){
            System.out.println(fields.get(i));
            i++;
        }
    }
}
