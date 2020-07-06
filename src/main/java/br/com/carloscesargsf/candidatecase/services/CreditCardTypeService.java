package br.com.carloscesargsf.candidatecase.services;

import br.com.carloscesargsf.candidatecase.dtos.CreditCardTypeListItemDTO;
import br.com.carloscesargsf.candidatecase.dtos.PaginatedItemsDTO;
import br.com.carloscesargsf.candidatecase.filters.CreditCardTypeFilter;
import br.com.carloscesargsf.candidatecase.mappers.CreditCardTypeMapper;
import br.com.carloscesargsf.candidatecase.repositories.CreditCardTypeRepository;
import br.com.carloscesargsf.candidatecase.services.base.BaseService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditCardTypeService extends BaseService {

    private final CreditCardTypeRepository creditCardTypeRepository;

    public CreditCardTypeService(CreditCardTypeRepository creditCardTypeRepository) {
        this.creditCardTypeRepository = creditCardTypeRepository;
    }

    @Transactional(readOnly = true)
    public PaginatedItemsDTO<CreditCardTypeListItemDTO> findAll(CreditCardTypeFilter filters) {
        logCurrentMethodExecution(filters);

        return new PaginatedItemsDTO<>(CreditCardTypeMapper.toCreditCardTypeListItemDTO(filters.getPage().isPresent()
                ? creditCardTypeRepository.findAll(filters, PageRequest.of(filters.getPage().get(),
                filters.getPageSize().isPresent()
                        ? filters.getPageSize().get()
                        : preferencesDefaultProperties.getPageSize()))
                : new PageImpl<>(creditCardTypeRepository.findAll(filters))
        ));
    }

}
