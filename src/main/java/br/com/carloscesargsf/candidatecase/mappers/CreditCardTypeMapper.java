package br.com.carloscesargsf.candidatecase.mappers;

import br.com.carloscesargsf.candidatecase.dtos.CreditCardTypeListItemDTO;
import br.com.carloscesargsf.candidatecase.entities.CreditCardType;
import org.springframework.data.domain.Page;

public class CreditCardTypeMapper {

    private CreditCardTypeMapper() {
    }

    public static CreditCardTypeListItemDTO toCreditCardTypeListItemDTO(CreditCardType creditCardType) {
        CreditCardTypeListItemDTO creditCardTypeListItemDTO = new CreditCardTypeListItemDTO();

        creditCardTypeListItemDTO.setId(creditCardType.getId());
        creditCardTypeListItemDTO.setName(creditCardType.getName());

        return creditCardTypeListItemDTO;
    }

    public static Page<CreditCardTypeListItemDTO> toCreditCardTypeListItemDTO(Page<CreditCardType> creditCardTypes) {
        return creditCardTypes.map(CreditCardTypeMapper::toCreditCardTypeListItemDTO);
    }

}
