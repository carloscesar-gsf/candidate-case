package br.com.carloscesargsf.candidatecase.filters.base;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

public abstract class BaseFilter<E> implements Specification<E> {

    private static final long serialVersionUID = -7501552669616615124L;

    @Schema(description = "Page to retrieve.", example = "1")
    @Parameter(in = ParameterIn.QUERY)
    private Optional<@Min(1) Integer> page = empty();

    @Schema(description = "Number of elements per page.", example = "10")
    @Parameter(in = ParameterIn.QUERY)
    private Optional<@Min(1) Integer> pageSize = empty();

    public BaseFilter() {
    }

    public Optional<Integer> getPage() {
        return page.isPresent() ? ofNullable(page.get() - 1) : empty();
    }

    public void setPage(Optional<Integer> page) {
        this.page = page;
    }

    public Optional<Integer> getPageSize() {
        return pageSize;
    }

    public void setPageSize(Optional<Integer> pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public Predicate toPredicate(Root<E> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        defineFilters(root, criteriaQuery, criteriaBuilder, predicates);

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    protected abstract void defineFilters(Root<E> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder,
                                          List<Predicate> predicates);

    protected final String like(String param) {
        return "%" + param + "%";
    }

    protected final String likeLowerCase(String param) {
        return like(param.toLowerCase());
    }

}
