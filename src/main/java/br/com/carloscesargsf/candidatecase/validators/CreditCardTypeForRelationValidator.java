package br.com.carloscesargsf.candidatecase.validators;

import br.com.carloscesargsf.candidatecase.entities.CreditCardType;
import br.com.carloscesargsf.candidatecase.repositories.CreditCardTypeRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class CreditCardTypeForRelationValidator implements ConstraintValidator<CreditCardTypeForRelationValidations, Long> {

    private final CreditCardTypeRepository creditCardTypeRepository;

    public CreditCardTypeForRelationValidator(CreditCardTypeRepository creditCardTypeRepository) {
        this.creditCardTypeRepository = creditCardTypeRepository;
    }

    @Override
    public boolean isValid(Long creditCardTypeId, ConstraintValidatorContext constraintValidatorContext) {
        if(creditCardTypeId == null) {
            return false;
        }

        Optional<CreditCardType> creditCardType = creditCardTypeRepository.findById(creditCardTypeId);

        return creditCardType.isPresent();
    }

}
