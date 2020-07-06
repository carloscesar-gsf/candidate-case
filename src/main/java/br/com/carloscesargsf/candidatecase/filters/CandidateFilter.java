package br.com.carloscesargsf.candidatecase.filters;

import br.com.carloscesargsf.candidatecase.entities.Candidate;
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
public class CandidateFilter extends BaseFilter<Candidate> {

    private static final long serialVersionUID = -487841120761067676L;

    @Schema(description = "Part of candidate's name to search.", example = "Carlos")
    @Parameter(in = ParameterIn.QUERY)
    private Optional<String> nameLike = empty();

    @Schema(description = "Candidate's name to search.", example = "Carlos César Gomes dos Santos Filho")
    @Parameter(in = ParameterIn.QUERY)
    private Optional<String> nameEquals = empty();

    @Schema(description = "Part of candidate's CPF to search.", example = "111")
    @Parameter(in = ParameterIn.QUERY)
    private Optional<String> cpfLike = empty();

    @Schema(description = "Candidate's CPF to search.", example = "11111111111")
    @Parameter(in = ParameterIn.QUERY)
    private Optional<String> cpfEquals = empty();

    @Schema(description = "Part of candidate's e-mail to search.", example = "carlos")
    @Parameter(in = ParameterIn.QUERY)
    private Optional<String> emailLike = empty();

    @Schema(description = "Candidate's e-mail to search.", example = "carloscesar.gsf@gmail.com")
    @Parameter(in = ParameterIn.QUERY)
    private Optional<String> emailEquals = empty();

    public CandidateFilter nameLike(String name) {
        setNameLike(ofNullable(name));
        return this;
    }

    public CandidateFilter nameEquals(String name) {
        setNameEquals(ofNullable(name));
        return this;
    }

    public CandidateFilter cpfLike(String cpf) {
        setCpfLike(ofNullable(cpf));
        return this;
    }

    public CandidateFilter cpfEquals(String cpf) {
        setCpfEquals(ofNullable(cpf));
        return this;
    }

    public CandidateFilter emailLike(String email) {
        setEmailLike(ofNullable(email));
        return this;
    }

    public CandidateFilter emailEquals(String email) {
        setEmailEquals(ofNullable(email));
        return this;
    }

    public Optional<String> getNameLike() {
        return nameLike;
    }

    public void setNameLike(Optional<String> nameLike) {
        this.nameLike = nameLike;
    }

    public Optional<String> getNameEquals() {
        return nameEquals;
    }

    public void setNameEquals(Optional<String> nameEquals) {
        this.nameEquals = nameEquals;
    }

    public Optional<String> getCpfLike() {
        return cpfLike;
    }

    public void setCpfLike(Optional<String> cpfLike) {
        this.cpfLike = cpfLike;
    }

    public Optional<String> getCpfEquals() {
        return cpfEquals;
    }

    public void setCpfEquals(Optional<String> cpfEquals) {
        this.cpfEquals = cpfEquals;
    }

    public Optional<String> getEmailLike() {
        return emailLike;
    }

    public void setEmailLike(Optional<String> emailLike) {
        this.emailLike = emailLike;
    }

    public Optional<String> getEmailEquals() {
        return emailEquals;
    }

    public void setEmailEquals(Optional<String> emailEquals) {
        this.emailEquals = emailEquals;
    }

    @Override
    protected void defineFilters(Root<Candidate> root, CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder, List<Predicate> predicates) {
        nameLike.ifPresent(name -> predicates.add(criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")), likeLowerCase(name))));

        nameEquals.ifPresent(name -> predicates.add(criteriaBuilder.equal(
                criteriaBuilder.lower(root.get("name")), name.toLowerCase())));

        cpfLike.ifPresent(cpf -> predicates.add(criteriaBuilder.like(
                criteriaBuilder.lower(root.get("cpf")), like(cpf))));

        cpfEquals.ifPresent(cpf -> predicates.add(criteriaBuilder.equal(
                criteriaBuilder.lower(root.get("cpf")), cpf)));

        emailLike.ifPresent(email -> predicates.add(criteriaBuilder.like(
                criteriaBuilder.lower(root.get("email")), likeLowerCase(email))));

        emailEquals.ifPresent(email -> predicates.add(criteriaBuilder.equal(
                criteriaBuilder.lower(root.get("email")), email.toLowerCase())));

        criteriaQuery.orderBy(criteriaBuilder.asc(criteriaBuilder.lower(root.get("name"))),
                criteriaBuilder.asc(root.get("dateOfBirth")));
    }

}
