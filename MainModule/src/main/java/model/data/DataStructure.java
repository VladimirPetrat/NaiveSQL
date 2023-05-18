package model.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataStructure {
    private String fieldName;
    private Object value;
    private Class type;
}

