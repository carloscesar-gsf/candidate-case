package br.com.carloscesargsf.candidatecase.repositories;

import br.com.carloscesargsf.candidatecase.entities.Candidate;
import br.com.carloscesargsf.candidatecase.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends BaseRepository<Candidate, Long> {
}
