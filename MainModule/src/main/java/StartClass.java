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

        HashMap<String, String> data = new HashMap<>();
        data.put("first name", "Andrew");
        data.put("second name", "Kushyk");
        data.put("age", "21");
        String id = myTable.addRow(data);

        System.out.println("id = " + id);
        System.out.println(myTable);

//        myTable.removeRow(id);
//        myTable.removeRow(id);
//        System.out.println(myTable);
//        System.out.println(myTable.rowIsEmpty());

        HashMap<String, String> newData = new HashMap<>();
        newData.put("first name", "Oleg");
        newData.put("second name", "New");
//        myTable.updateRowFieldValues(id, newData);
//        myTable.replaceRowFieldValues(id, newData);
        System.out.println(myTable);

    }
}