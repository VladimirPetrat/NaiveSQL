import models.PersonDTO;

public class StartClass {

    public static void main(String... args) {

        PersonDTO me = new PersonDTO("1", "Andy", "2001");

        System.out.println(me.returnPersonDTO());

        me.replaceID("2");
        me.replaceName("Andrew");
        me.replaceYear("1999");
        System.out.println(me.returnPersonDTO());

        System.out.println("\nID = " + me.returnId() +
                            "\nName = " + me.returnName()+
                            "\nYear = " + me.returnYear());
    }
}
