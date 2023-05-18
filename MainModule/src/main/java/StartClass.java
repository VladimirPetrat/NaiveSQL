import model.data.DataHandler;
import model.data.DataObject;
import model.data.Table;

import java.util.HashSet;
import java.util.List;

public class StartClass {

    public static void main(String... args) {

        DataObject olegName= new DataObject("name", "Oleg", String.class);
        DataObject olegAge = new DataObject("age", 280, Integer.class);

        List<DataObject> dataStructures = List.of(olegName, olegAge);
        
        HashSet<String> set = new HashSet<>();
        set.add("name");
        set.add("age");

        Table table = new DataHandler().createNewTable("Table", set);
        String id = table.addRow(dataStructures);
        DataObject age = table.getDataForFieldName(id, "age");
        Object o = age
                .getType()
                .cast(age.getValue());

        System.out.println(o);
    }
}