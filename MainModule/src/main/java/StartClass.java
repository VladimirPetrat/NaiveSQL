import model.data.TableHolder;

import java.util.HashMap;
import java.util.HashSet;

public class StartClass {

    public static void main(String... args) {
        HashSet<String> columns = new HashSet<>();
        columns.add("first name");
        columns.add("second name");
        columns.add("age");
        TableHolder myTable = new TableHolder(columns);

        HashMap<String, String> data = new HashMap<>();
        data.put("first name", "Andrew");
        data.put("second name", "Kushyk");
        data.put("age", "21");
        String id = myTable.addRow(data);

        System.out.println("id = " + id);
        System.out.println(myTable);
    }
}