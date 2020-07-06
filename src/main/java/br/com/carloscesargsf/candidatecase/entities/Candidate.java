package br.com.carloscesargsf.candidatecase.entities;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_candidates")
public class Candidate implements Serializable {

    private static final long serialVersionUID = 8842669231265931519L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, unique = true)
    private Long id;

    @NotBlank
    @Column(length = 200)
    private String name;

    @CPF
    @NotBlank
    @Column(length = 11, unique = true)
    private String cpf;

    @NotNull
    @Column
    private LocalDate dateOfBirth;

    @NotBlank
    @Column(length = 200, unique = true)
    private String email;

    @OneToMany(mappedBy = "candidate")
    private Set<CreditCard> creditCards = new HashSet<>();

    public Candidate() {
    }

    public Candidate(Long id) {
        setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public Set<CreditCard> getCreditCards() {
        return creditCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id.equals(candidate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
