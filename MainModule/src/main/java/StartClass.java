import model.data.DataHandler;
import model.data.DataObject;
import model.data.Table;

import java.util.HashSet;
import java.util.List;

public class StartClass {

    public static void main(String... args) {

        DataObject olegName = new DataObject("name", "Oleg", String.class);
        DataObject olegAge = new DataObject("age", 280, Integer.class);

        List<DataObject> dataStructures = List.of(olegName, olegAge);

        HashSet<String> set = new HashSet<>();
        set.add("name");
        set.add("age");

        Table table = new DataHandler().createNewTable("Table", set);
        String id = table.addRow(dataStructures);

        DataObject an = new DataObject("name", "Andrew", String.class);
        DataObject an_age = new DataObject("age", 21, Integer.class);

        List<DataObject> newData = List.of(an, an_age);
        table.updateRowFieldValues(id, newData);
        Object age = table.getFieldValue(id, "age");

        Object name = table.getFieldValue(id, "name");
        System.out.println(name + " " + age);

        Object o3 = table.getFieldValue(id, "age");
        System.out.println(o3);
    }
}