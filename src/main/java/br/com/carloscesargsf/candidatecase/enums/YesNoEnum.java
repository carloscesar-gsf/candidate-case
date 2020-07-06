package br.com.carloscesargsf.candidatecase.enums;

import java.util.HashMap;
import java.util.Map;

public enum YesNoEnum {

    NO(0, "No"),
    YES(1, "Yes");

    public static Map<Integer, YesNoEnum> OPTIONS = new HashMap<>();

    static {
        for (YesNoEnum option : YesNoEnum.values()) {
            OPTIONS.put(option.getId(), option);
        }
    }

    private Integer id;

    private String description;

    YesNoEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "YesNoEnum{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

}
