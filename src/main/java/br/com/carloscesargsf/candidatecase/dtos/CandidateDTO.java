package br.com.carloscesargsf.candidatecase.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDate;

public class CandidateDTO implements Serializable {

    private static final long serialVersionUID = -6077395997996906151L;

    @Schema(description = "Candidate's ID.", example = "1")
    private Long id;

    @Schema(description = "Candidate's name.", example = "Carlos César Gomes dos Santos Filho")
    private String name;

    @Schema(description = "Candidate's CPF.", example = "11111111111")
    private String cpf;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Candidate's date of birth.", example = "1988-04-12")
    private LocalDate dateOfBirth;

    @Schema(description = "Candidate's e-mail.", example = "carloscesar.gsf@gmail.com")
    private String email;

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
        this.name = name;
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
        this.email = email;
    }

    @Override
    public String toString() {
        return "CandidateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                '}';
    }

}
