package br.com.carloscesargsf.candidatecase.enums;

import java.util.HashMap;
import java.util.Map;

public enum GenderEnum {

    MALE("M", "Male"),
    FEMALE("F", "Female");

    public static Map<String, GenderEnum> OPTIONS = new HashMap<>();

    static {
        for (GenderEnum option : GenderEnum.values()) {
            OPTIONS.put(option.getId(), option);
        }
    }

    private String id;

    private String description;

    GenderEnum(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "GenderEnum{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
