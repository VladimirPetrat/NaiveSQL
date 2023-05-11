import models.PersonDTO;

public class StartClass {

    public static void main(String... args) {

        PersonDTO me = new PersonDTO("1", "Andy", "2001");

        System.out.println(me.returnPersonDTO());

        me.replaceField("id", "2");
        me.replaceField("name", "Andrew");
        me.replaceField("year", "1999");
        System.out.println(me.returnPersonDTO());

        me.removeField("year");
        System.out.println(System.lineSeparator() + "ID = " + me.returnField("id") +
                            System.lineSeparator() + "Name = " + me.returnField("name")+
                            System.lineSeparator() + "Year = " + me.returnField("year"));

        me.addField("second_name", "Kushyk");
        System.out.println(me.returnPersonDTO());
    }
}
