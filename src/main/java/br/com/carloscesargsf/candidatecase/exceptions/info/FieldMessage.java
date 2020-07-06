package br.com.carloscesargsf.candidatecase.exceptions.info;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "Error message of a field.")
public class FieldMessage implements Serializable {

    private static final long serialVersionUID = -7531238812613795178L;

    @Schema(description = "Field name.", example = "id")
    private String fieldName;

    @Schema(description = "Error message", example = "Cannot be null.")
    private String message;

    public FieldMessage(String fieldName, String message) {
        setFieldName(fieldName);
        setMessage(message);
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FieldMessage{" +
                "fieldName='" + fieldName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
