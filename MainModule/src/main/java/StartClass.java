import models.PersonDTO;

public class StartClass {

    public static void main(String... args) {

        PersonDTO me = new PersonDTO();
        System.out.println(me.returnPersonDTO());
    }
}