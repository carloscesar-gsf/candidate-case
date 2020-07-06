package br.com.carloscesargsf.candidatecase.constants;

public class ExceptionConstants {

    public static final String ENTER_IN_CONTACT_WITH_THE_ADMINISTRATOR = "Contact the Administrator.";

    public static final String MESSAGE_WITHOUT_READ_PERMISSIONS = "User cannot read the entity. " +
            ENTER_IN_CONTACT_WITH_THE_ADMINISTRATOR;
    public static final String MESSAGE_WITHOUT_SAVE_PERMISSIONS = "User cannot save the entity. " +
            ENTER_IN_CONTACT_WITH_THE_ADMINISTRATOR;
    public static final String MESSAGE_WITHOUT_DELETE_PERMISSIONS = "User cannot delete the entity. " +
            ENTER_IN_CONTACT_WITH_THE_ADMINISTRATOR;

    public static final String METHOD_PARAM_IS_MANDATORY = "Param '%s' is mandatory.";
    public static final String METHOD_PARAM_ID_IS_MANDATORY = String.format(METHOD_PARAM_IS_MANDATORY, "id");

    public static final String METHOD_PARAM_MUST_BE_NULL = "Param '%s' must be null.";
    public static final String METHOD_PARAM_ID_MUST_BE_NULL = String.format(METHOD_PARAM_MUST_BE_NULL, "id");

    public static final String INFO_MUST_BE_UNIQUE = "'%s' must be unique.";

    public static final String ENTITY_BEING_UPDATED_IS_DIFFERENT_FROM_ENTITY_SENT =
            "Entity being updated is different from entity sent.";

    public static final String RESOURCE_NOT_FOUND = "Resource not found.";

    private ExceptionConstants() {
        // $COVERAGE-IGNORE$
    }

}
