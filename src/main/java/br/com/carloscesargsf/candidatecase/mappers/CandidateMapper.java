package br.com.carloscesargsf.candidatecase.mappers;

import br.com.carloscesargsf.candidatecase.dtos.CandidateDTO;
import br.com.carloscesargsf.candidatecase.dtos.CandidateListItemDTO;
import br.com.carloscesargsf.candidatecase.entities.Candidate;
import org.springframework.data.domain.Page;

public class CandidateMapper {

    private CandidateMapper() {
    }

    public static CandidateListItemDTO toCandidateListItemDTO(Candidate candidate) {
        CandidateListItemDTO candidateListItemDTO = new CandidateListItemDTO();

        candidateListItemDTO.setId(candidate.getId());
        candidateListItemDTO.setName(candidate.getName());
        candidateListItemDTO.setCpf(candidate.getCpf());
        candidateListItemDTO.setDateOfBirth(candidate.getDateOfBirth());
        candidateListItemDTO.setEmail(candidate.getEmail());

        return candidateListItemDTO;
    }

    public static Page<CandidateListItemDTO> toCandidateListItemDTO(Page<Candidate> candidates) {
        return candidates.map(CandidateMapper::toCandidateListItemDTO);
    }

    public static CandidateDTO toCandidateDTO(Candidate candidate) {
        CandidateDTO candidateDTO = new CandidateDTO();

        candidateDTO.setId(candidate.getId());
        candidateDTO.setName(candidate.getName());
        candidateDTO.setCpf(candidate.getCpf());
        candidateDTO.setDateOfBirth(candidate.getDateOfBirth());
        candidateDTO.setEmail(candidate.getEmail());

        return candidateDTO;
    }

}
