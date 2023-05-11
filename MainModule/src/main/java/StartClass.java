import models.PersonDTO;

public class StartClass {

    public static void main(String... args) {

        PersonDTO me = new PersonDTO("1", "Andy", "2001");

        System.out.println(me.returnPersonDTO());

        me.replaceField("id", "2");
        me.replaceField("name", "Andrew");
        me.replaceField("year", "1999");
        System.out.println(me.returnPersonDTO());

        System.out.println("\nID = " + me.returnField("id") +
                            "\nName = " + me.returnField("name")+
                            "\nYear = " + me.returnField("year"));
    }
}
