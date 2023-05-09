import models.Person;

public class StartClass {

    public static void main(String... args) {

        //an object for tests
        Person me = new Person("1", "Andy", "2001");
        //testing an output function
        me.printPerson();
    }
}
