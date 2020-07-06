package br.com.carloscesargsf.candidatecase.filters;

import br.com.carloscesargsf.candidatecase.entities.CreditCard;
import br.com.carloscesargsf.candidatecase.filters.base.BaseFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@Schema(name = "CreditCardFilter")
public class CreditCardFilter extends BaseFilter<CreditCard> {

    private static final long serialVersionUID = -487841120761067676L;

    @Schema(description = "ID of the candidate to search.", example = "1")
    @Parameter(in = ParameterIn.QUERY)
    @JsonIgnore
    private Optional<Long> candidateId = empty();

    @Schema(description = "Part of credit card's number to search.", example = "1111")
    @Parameter(in = ParameterIn.QUERY)
    private Optional<String> numberLike = empty();

    @Schema(description = "Credit card's number to search.", example = "1111111111111111")
    @Parameter(in = ParameterIn.QUERY)
    private Optional<String> numberEquals = empty();

    public CreditCardFilter candidateId(Long candidateId) {
        setCandidateId(ofNullable(candidateId));
        return this;
    }

    public CreditCardFilter numberLike(String number) {
        setNumberLike(ofNullable(number));
        return this;
    }

    public CreditCardFilter numberEquals(String number) {
        setNumberEquals(ofNullable(number));
        return this;
    }

    public Optional<Long> getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Optional<Long> candidateId) {
        this.candidateId = candidateId;
    }

    public Optional<String> getNumberLike() {
        return numberLike;
    }

    public void setNumberLike(Optional<String> numberLike) {
        this.numberLike = numberLike;
    }

    public Optional<String> getNumberEquals() {
        return numberEquals;
    }

    public void setNumberEquals(Optional<String> numberEquals) {
        this.numberEquals = numberEquals;
    }

    @Override
    protected void defineFilters(Root<CreditCard> root, CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        candidateId.ifPresent(c -> predicates.add(criteriaBuilder.equal(
                criteriaBuilder.lower(root.get("candidate").get("id")), c)));

        numberLike.ifPresent(number -> predicates.add(criteriaBuilder.like(
                root.get("number"), number)));

        numberEquals.ifPresent(number -> predicates.add(criteriaBuilder.equal(
                root.get("number"), number)));

        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("expiresOn")));
    }

}
