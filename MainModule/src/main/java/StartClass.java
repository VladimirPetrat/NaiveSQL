import models.PersonDTO;

public class StartClass {

    public static void main(String... args) {

        //an object for tests
        PersonDTO me = new PersonDTO("1", "Andy", "2001");
        //testing an output function
        me.printPerson();
    }
}
