package br.com.carloscesargsf.candidatecase.mappers;

import br.com.carloscesargsf.candidatecase.dtos.CreditCardDTO;
import br.com.carloscesargsf.candidatecase.dtos.CreditCardListItemDTO;
import br.com.carloscesargsf.candidatecase.entities.CreditCard;
import org.springframework.data.domain.Page;

public class CreditCardMapper {

    private CreditCardMapper() {
    }

    public static CreditCardListItemDTO toCreditCardListItemDTO(CreditCard creditCard) {
        CreditCardListItemDTO creditCardListItemDTO = new CreditCardListItemDTO();

        creditCardListItemDTO.setId(creditCard.getId());
        creditCardListItemDTO.setCandidateId(creditCard.getCandidate().getId());
        creditCardListItemDTO.setCandidateName(creditCard.getCandidate().getName());
        creditCardListItemDTO.setCandidateCpf(creditCard.getCandidate().getCpf());
        creditCardListItemDTO.setCreditCardTypeId(creditCard.getCreditCardType().getId());
        creditCardListItemDTO.setCreditCardTypeName(creditCard.getCreditCardType().getName());
        creditCardListItemDTO.setName(creditCard.getName());
        creditCardListItemDTO.setNumber(creditCard.getNumber());
        creditCardListItemDTO.setExpiresOn(creditCard.getExpiresOn());

        return creditCardListItemDTO;
    }

    public static Page<CreditCardListItemDTO> toCreditCardListItemDTO(Page<CreditCard> creditCards) {
        return creditCards.map(CreditCardMapper::toCreditCardListItemDTO);
    }

    public static CreditCardDTO toCreditCardDTO(CreditCard creditCard) {
        CreditCardDTO creditCardDTO = new CreditCardDTO();

        creditCardDTO.setId(creditCard.getId());
        creditCardDTO.setCandidateId(creditCard.getCandidate().getId());
        creditCardDTO.setCreditCardTypeId(creditCard.getCreditCardType().getId());
        creditCardDTO.setName(creditCard.getName());
        creditCardDTO.setNumber(creditCard.getNumber());
        creditCardDTO.setExpiresOn(creditCard.getExpiresOn());

        return creditCardDTO;
    }

}
