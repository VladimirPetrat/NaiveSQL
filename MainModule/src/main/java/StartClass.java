import java.util.HashMap;

import models.PersonDTO;

public class StartClass {

    public static void main(String... args) {

        PersonDTO me = new PersonDTO("1", "Andy", "2001");

        System.out.println(me.returnPersonDTO());

        me.replaceField("id", "2");
        me.replaceField("name", "Andrew");
        me.replaceField("year", "1999");
        System.out.println(me.returnPersonDTO());

        // me.removeField("year");
        me.replaceField("year", "");
        System.out.println(System.lineSeparator() + "ID = " + me.returnField("id") +
                            System.lineSeparator() + "Name = " + me.returnField("name")+
                            System.lineSeparator() + "Year = " + me.returnField("year"));

        me.addField("second_name", "Kushyk");
        System.out.println(me.returnPersonDTO());

        System.out.println(me.fieldExists("year"));
        System.out.println(me.fieldIsEmpty("year"));

        PersonDTO newPer = new PersonDTO();

        HashMap<String, String> dto = new HashMap<>();
        dto.put("name", "usual");
        dto.put("second_name", "unusual");

        newPer.addPersonDTO(dto);
        System.out.println(newPer.returnPersonDTO());

        newPer.removePersonDTO();
        System.out.println(newPer.returnPersonDTO());
    }
}