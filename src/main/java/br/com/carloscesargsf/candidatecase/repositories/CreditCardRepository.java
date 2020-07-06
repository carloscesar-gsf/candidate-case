package br.com.carloscesargsf.candidatecase.repositories;

import br.com.carloscesargsf.candidatecase.entities.CreditCard;
import br.com.carloscesargsf.candidatecase.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends BaseRepository<CreditCard, Long> {
}
