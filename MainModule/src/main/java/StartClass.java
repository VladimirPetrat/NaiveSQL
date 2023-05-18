import model.data.DataHandler;
import model.data.DataStructure;
import model.data.Table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class StartClass {

    public static void main(String... args) {

        DataStructure olegName = new DataStructure("name", "Oleg", String.class);
        DataStructure olegAge = new DataStructure("age", 280, Integer.class);

        List<DataStructure> dataStructures = List.of(olegName, olegAge);
        
        HashSet<String> set = new HashSet<>();
        set.add("name");
        set.add("age");

        Table table = new DataHandler().createNewTable("Table", set);
        String id = table.addRow(dataStructures);
        DataStructure age = table.getDataForFieldName(id, "age");
        Object o = age
                .getType()
                .cast(age.getValue());

        System.out.println(o);
    }
}