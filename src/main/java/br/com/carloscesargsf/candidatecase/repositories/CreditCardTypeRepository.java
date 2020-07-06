package br.com.carloscesargsf.candidatecase.repositories;

import br.com.carloscesargsf.candidatecase.entities.CreditCardType;
import br.com.carloscesargsf.candidatecase.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardTypeRepository extends BaseRepository<CreditCardType, Long> {
}
