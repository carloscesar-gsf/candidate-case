package br.com.carloscesargsf.candidatecase.services;

import br.com.carloscesargsf.candidatecase.dtos.CreditCardCreateDTO;
import br.com.carloscesargsf.candidatecase.dtos.CreditCardDTO;
import br.com.carloscesargsf.candidatecase.dtos.CreditCardListItemDTO;
import br.com.carloscesargsf.candidatecase.dtos.PaginatedItemsDTO;
import br.com.carloscesargsf.candidatecase.entities.Candidate;
import br.com.carloscesargsf.candidatecase.entities.CreditCard;
import br.com.carloscesargsf.candidatecase.entities.CreditCardType;
import br.com.carloscesargsf.candidatecase.exceptions.ApiBusinessException;
import br.com.carloscesargsf.candidatecase.exceptions.MethodIllegalArgumentException;
import br.com.carloscesargsf.candidatecase.exceptions.ResourceNotFoundException;
import br.com.carloscesargsf.candidatecase.filters.CreditCardFilter;
import br.com.carloscesargsf.candidatecase.mappers.CreditCardMapper;
import br.com.carloscesargsf.candidatecase.repositories.CreditCardRepository;
import br.com.carloscesargsf.candidatecase.services.base.BaseService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static br.com.carloscesargsf.candidatecase.constants.ExceptionConstants.INFO_MUST_BE_UNIQUE;
import static br.com.carloscesargsf.candidatecase.constants.ExceptionConstants.METHOD_PARAM_ID_IS_MANDATORY;

@Service
public class CreditCardService extends BaseService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Transactional(readOnly = true)
    public PaginatedItemsDTO<CreditCardListItemDTO> findAll(CreditCardFilter filters) {
        logCurrentMethodExecution(filters);

        return new PaginatedItemsDTO<>(CreditCardMapper.toCreditCardListItemDTO(filters.getPage().isPresent()
                ? creditCardRepository.findAll(filters, PageRequest.of(filters.getPage().get(),
                filters.getPageSize().isPresent()
                        ? filters.getPageSize().get()
                        : preferencesDefaultProperties.getPageSize()))
                : new PageImpl<>(creditCardRepository.findAll(filters))
        ));
    }

    @Transactional(readOnly = true)
    public CreditCardDTO findById(Long id) {
        logCurrentMethodExecution(id);

        if (id == null) {
            throw new MethodIllegalArgumentException(METHOD_PARAM_ID_IS_MANDATORY);
        }

        return creditCardRepository.findById(id)
                .map(CreditCardMapper::toCreditCardDTO)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    @Transactional
    public CreditCardDTO create(CreditCardCreateDTO creditCardCreateDTO) {
        logCurrentMethodExecution(creditCardCreateDTO);

        validateUniqueNumber(creditCardCreateDTO.getNumber());
        LocalDate expiresOn = generateExpiresOn(creditCardCreateDTO.getExpiresOnYear(),
                creditCardCreateDTO.getExpiresOnMonth());

        CreditCard creditCard = new CreditCard();

        creditCard.setCandidate(new Candidate(creditCardCreateDTO.getCandidateId()));
        creditCard.setCreditCardType(new CreditCardType(creditCardCreateDTO.getCreditCardTypeId()));
        creditCard.setName(creditCardCreateDTO.getName());
        creditCard.setNumber(creditCardCreateDTO.getNumber());
        creditCard.setExpiresOn(expiresOn);

        return CreditCardMapper.toCreditCardDTO(creditCardRepository.save(creditCard));
    }

    @Transactional
    public void deleteByID(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());

        creditCardRepository.delete(creditCard);
    }

    private void validateUniqueNumber(String number) {
        CreditCardFilter creditCardFilter = new CreditCardFilter()
                .numberEquals(number);

        PaginatedItemsDTO<CreditCardListItemDTO> page = findAll(creditCardFilter);

        if (!page.getContent().isEmpty()) {
            throw new ApiBusinessException(INFO_MUST_BE_UNIQUE, "Credit card number");
        }
    }

    private LocalDate generateExpiresOn(Integer expiresOnYear, Integer expiresOnMonth) {
        LocalDate expiresOn = LocalDate.now()
                .withYear(expiresOnYear)
                .withMonth(expiresOnMonth);
        expiresOn = expiresOn.withDayOfMonth(expiresOn.lengthOfMonth());

        if (expiresOn.isBefore(LocalDate.now())) {
            throw new ApiBusinessException("Credit card expired.");
        }

        return expiresOn;
    }

}
