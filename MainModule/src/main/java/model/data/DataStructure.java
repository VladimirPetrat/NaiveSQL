package model.data;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class DataStructure<T> {
    private String key;
    private T value;

    public DataStructure(){}

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public boolean isEmpty() {
        return key != null && value != null;
    }

    public Collection<?> keySet() {
        Set<String> keys = new HashSet<>();
        if (key != null) {
            keys.add(key);
        }
        return keys;
    }

    public void putAll(DataStructure<T> rowDataPackage) {
        this.key = rowDataPackage.key;
        this.value = rowDataPackage.value;
    }


    public T getOrDefault(Object field, Object defaultValue) {
        if (field.equals(key)) {
            return value;
        } else {
            return (T) defaultValue;
        }
    }

    public void put(Object field, T value) {
        this.key = (String) field;
        this.value = value;
    }
}

