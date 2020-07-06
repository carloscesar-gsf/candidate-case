package br.com.carloscesargsf.candidatecase.services;

import br.com.carloscesargsf.candidatecase.dtos.*;
import br.com.carloscesargsf.candidatecase.entities.Candidate;
import br.com.carloscesargsf.candidatecase.exceptions.ApiBusinessException;
import br.com.carloscesargsf.candidatecase.exceptions.MethodIllegalArgumentException;
import br.com.carloscesargsf.candidatecase.exceptions.ResourceNotFoundException;
import br.com.carloscesargsf.candidatecase.filters.CandidateFilter;
import br.com.carloscesargsf.candidatecase.filters.CreditCardFilter;
import br.com.carloscesargsf.candidatecase.mappers.CandidateMapper;
import br.com.carloscesargsf.candidatecase.repositories.CandidateRepository;
import br.com.carloscesargsf.candidatecase.services.base.BaseService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.carloscesargsf.candidatecase.constants.ExceptionConstants.*;

@Service
public class CandidateService extends BaseService {

    private final CandidateRepository candidateRepository;

    private final CreditCardService creditCardService;

    public CandidateService(CandidateRepository candidateRepository,
                            CreditCardService creditCardService) {
        this.candidateRepository = candidateRepository;
        this.creditCardService = creditCardService;
    }

    @Transactional(readOnly = true)
    public PaginatedItemsDTO<CandidateListItemDTO> findAll(CandidateFilter filters) {
        logCurrentMethodExecution(filters);

        return new PaginatedItemsDTO<>(CandidateMapper.toCandidateListItemDTO(filters.getPage().isPresent()
                ? candidateRepository.findAll(filters, PageRequest.of(filters.getPage().get(),
                filters.getPageSize().isPresent()
                        ? filters.getPageSize().get()
                        : preferencesDefaultProperties.getPageSize()))
                : new PageImpl<>(candidateRepository.findAll(filters))
        ));
    }

    @Transactional(readOnly = true)
    public CandidateDTO findById(Long id) {
        logCurrentMethodExecution(id);

        if (id == null) {
            throw new MethodIllegalArgumentException(METHOD_PARAM_ID_IS_MANDATORY);
        }

        return candidateRepository.findById(id)
                .map(CandidateMapper::toCandidateDTO)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    @Transactional
    public CandidateDTO create(CandidateCreateDTO candidateCreateDTO) {
        logCurrentMethodExecution(candidateCreateDTO);

        validateUniqueCpf(candidateCreateDTO.getCpf());
        validateUniqueEmail(candidateCreateDTO.getEmail());

        Candidate candidate = new Candidate();

        candidate.setName(candidateCreateDTO.getName());
        candidate.setCpf(candidateCreateDTO.getCpf());
        candidate.setDateOfBirth(candidateCreateDTO.getDateOfBirth());
        candidate.setEmail(candidateCreateDTO.getEmail());

        return CandidateMapper.toCandidateDTO(candidateRepository.save(candidate));
    }

    @Transactional
    public void update(Long id, CandidateUpdateDTO candidateUpdateDTO) {
        if (!id.equals(candidateUpdateDTO.getId())) {
            throw new ApiBusinessException(ENTITY_BEING_UPDATED_IS_DIFFERENT_FROM_ENTITY_SENT);
        }

        validateUniqueCpf(candidateUpdateDTO.getCpf(), id);
        validateUniqueEmail(candidateUpdateDTO.getEmail(), id);

        Candidate candidate = candidateRepository.findById(candidateUpdateDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException());

        candidate.setName(candidateUpdateDTO.getName());
        candidate.setCpf(candidateUpdateDTO.getCpf());
        candidate.setDateOfBirth(candidateUpdateDTO.getDateOfBirth());
        candidate.setEmail(candidateUpdateDTO.getEmail());

        candidateRepository.save(candidate);
    }

    @Transactional
    public void deleteByID(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());

        findAllCreditCards(id, new CreditCardFilter())
                .getContent()
                .forEach(creditCard -> deleteCreditCardById(id, creditCard.getId()));

        candidateRepository.delete(candidate);
    }

    @Transactional(readOnly = true)
    public PaginatedItemsDTO<CreditCardListItemDTO> findAllCreditCards(Long id, CreditCardFilter creditCardFilter) {
        return creditCardService.findAll(creditCardFilter.candidateId(id));
    }

    @Transactional(readOnly = true)
    public CreditCardDTO findCreditCardById(Long id, Long creditCardId) {
        CreditCardDTO creditCardDTO = creditCardService.findById(creditCardId);

        if (!creditCardDTO.getCandidateId().equals(id)) {
            throw new ResourceNotFoundException();
        }

        return creditCardDTO;
    }

    @Transactional
    public CreditCardDTO createCreditCard(Long id, CandidateCreditCardCreateDTO candidateCreditCardCreateDTO) {
        findById(id);

        CreditCardCreateDTO creditCardCreateDTO = new CreditCardCreateDTO();

        creditCardCreateDTO.setCandidateId(id);
        creditCardCreateDTO.setCreditCardTypeId(candidateCreditCardCreateDTO.getCreditCardTypeId());
        creditCardCreateDTO.setName(candidateCreditCardCreateDTO.getName());
        creditCardCreateDTO.setNumber(candidateCreditCardCreateDTO.getNumber());
        creditCardCreateDTO.setExpiresOnYear(candidateCreditCardCreateDTO.getExpiresOnYear());
        creditCardCreateDTO.setExpiresOnMonth(candidateCreditCardCreateDTO.getExpiresOnMonth());

        return creditCardService.create(creditCardCreateDTO);
    }

    @Transactional
    public void deleteCreditCardById(Long id, Long creditCardId) {
        findCreditCardById(id, creditCardId);

        creditCardService.deleteByID(creditCardId);
    }

    private void validateUniqueCpf(String cpf) {
        validateUniqueCpf(cpf, null);
    }

    private void validateUniqueCpf(String cpf, Long id) {
        CandidateFilter candidateFilter = new CandidateFilter()
                .cpfEquals(cpf);

        PaginatedItemsDTO<CandidateListItemDTO> page = findAll(candidateFilter);

        if (!page.getContent().isEmpty()
                && !(page.getContent().get(0)).getId().equals(id)) {
            throw new ApiBusinessException(INFO_MUST_BE_UNIQUE, "CPF");
        }
    }

    private void validateUniqueEmail(String email) {
        validateUniqueEmail(email, null);
    }

    private void validateUniqueEmail(String email, Long id) {
        CandidateFilter candidateFilter = new CandidateFilter()
                .emailEquals(email);

        PaginatedItemsDTO<CandidateListItemDTO> page = findAll(candidateFilter);

        if (!page.getContent().isEmpty()
                && !(page.getContent().get(0)).getId().equals(id)) {
            throw new ApiBusinessException(INFO_MUST_BE_UNIQUE, "E-mail");
        }
    }

}
