package model.data;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

public class TableRow {

    private final String incorrectFieldNameError = "Incorrect field name";

    @Getter
    private final List<DataObject> fields;

    public TableRow(List<DataObject> objects) {
        fields = objects;
    }

    public void addField(DataObject dataObject) {
        Optional<DataObject> dataOptional = findDataObject(dataObject.getFieldName());
        if (dataOptional.isEmpty()) {
            fields.add(dataObject);
        } else {
            throw new IllegalArgumentException("Field is already present");
        }
    }

    public void insertField(DataObject dataObject) {
        findDataObject(dataObject.getFieldName()).ifPresentOrElse(existingObject -> replaceExistingObjectValue(existingObject, dataObject), () -> {
            throw new IllegalArgumentException(incorrectFieldNameError);
        });
    }

    public void removeField(String fieldName) {
        findDataObject(fieldName).ifPresentOrElse(fields::remove, () -> {
                    throw new IllegalArgumentException(incorrectFieldNameError);
                }
        );
    }

    public void removeFieldValue(String fieldName) {
        //TODO Possible refactor due to DataObject updates
        findDataObject(fieldName).ifPresentOrElse(existingObject -> replaceExistingObjectValue(existingObject, new DataObject(fieldName, new Object(), Object.class)), () -> {
                    throw new IllegalArgumentException(incorrectFieldNameError);
                }
        );
    }

    public DataObject getFieldDataObject(String fieldName) {
        return findDataObject(fieldName).orElseThrow(() -> new IllegalArgumentException(incorrectFieldNameError));
    }

    public void replaceExistingObjectValue(DataObject currentObject, DataObject newObject) {
        currentObject.setType(newObject.getType());
        currentObject.setValue(newObject.getValue());
    }

    private Optional<DataObject> findDataObject(String fieldName) {
        return fields
                .stream()
                .filter(dataObject -> dataObject.getFieldName().equals(fieldName))
                .findFirst();
    }
}
