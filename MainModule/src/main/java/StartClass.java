import model.data.DataStructure;
import model.data.Table;

import java.util.HashMap;
import java.util.HashSet;

public class StartClass {

    public static void main(String... args) throws IllegalAccessException {
        HashSet<String> columns = new HashSet<>();
        columns.add("first name");
        columns.add("second name");
        columns.add("age");
        Table myTable = new Table(columns);

        /*
        HashMap<String, Object> data = new HashMap<>();
        data.put("first name", "Andrew");
        data.put("second name", "Kushyk");
        data.put("age", 21);
        String id = myTable.addRow(data);

        System.out.println("id = " + id);
        System.out.println(myTable);

//        myTable.removeRow(id);
//        myTable.removeRow(id);
//        System.out.println(myTable);
//        System.out.println(myTable.rowIsEmpty());

        HashMap<String, Object> newData = new HashMap<>();
        newData.put("first name", "Oleg");
        newData.put("age", 21);

//        myTable.updateRowFieldValues(id, newData);
//        myTable.replaceRowFieldValues(id, newData);
//        System.out.println(myTable.rowIsEmpty());
        System.out.println(myTable);

         */


        DataStructure<Integer> data2 = new DataStructure<>();
        data2.put("name", "Andrew");

        myTable.addRow(data2);

        System.out.println(myTable);

        // Print the values
        System.out.println("Key: " + key);
        System.out.println("Value: " + value);

    }
}