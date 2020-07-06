package br.com.carloscesargsf.candidatecase.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CreditCardTypeForRelationValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CreditCardTypeForRelationValidations {

    String message() default "Value not defined.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
