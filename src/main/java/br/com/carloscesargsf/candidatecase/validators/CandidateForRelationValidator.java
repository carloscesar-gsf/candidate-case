package br.com.carloscesargsf.candidatecase.validators;

import br.com.carloscesargsf.candidatecase.entities.Candidate;
import br.com.carloscesargsf.candidatecase.repositories.CandidateRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class CandidateForRelationValidator implements ConstraintValidator<CandidateForRelationValidations, Long> {

    private final CandidateRepository candidateRepository;

    public CandidateForRelationValidator(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public boolean isValid(Long candidateId, ConstraintValidatorContext constraintValidatorContext) {
        if(candidateId == null) {
            return false;
        }

        Optional<Candidate> candidate = candidateRepository.findById(candidateId);

        return candidate.isPresent();
    }

}
