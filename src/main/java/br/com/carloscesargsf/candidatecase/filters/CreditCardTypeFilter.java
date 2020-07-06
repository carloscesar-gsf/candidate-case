package br.com.carloscesargsf.candidatecase.filters;

import br.com.carloscesargsf.candidatecase.entities.CreditCardType;
import br.com.carloscesargsf.candidatecase.filters.base.BaseFilter;
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

@Schema(name = "CreditCardTypeFilter")
public class CreditCardTypeFilter extends BaseFilter<CreditCardType> {

    private static final long serialVersionUID = -487841120761067676L;

    @Schema(description = "Part of credit card type name to search.", example = "Master")
    @Parameter(in = ParameterIn.QUERY)
    private Optional<String> name = empty();

    public CreditCardTypeFilter name(String name) {
        setName(ofNullable(name));
        return this;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    @Override
    protected void defineFilters(Root<CreditCardType> root, CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        name.ifPresent(n -> predicates.add(criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")), likeLowerCase(n))));

        criteriaQuery.orderBy(criteriaBuilder.asc(criteriaBuilder.lower(root.get("name"))));
    }

}
