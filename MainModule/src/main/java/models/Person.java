package models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Person {

    //fields
    String id;
    String name;
    String yearOfBirth;

    //output function
    public void printPerson(){
        System.out.println("id: " + this.id + "\nname: " + this.name + "\nyear of birth: " + this.yearOfBirth);
    }
}
