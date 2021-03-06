package br.com.carloscesargsf.candidatecase.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

public class CandidateCreateDTO implements Serializable {

    private static final long serialVersionUID = -8151958967058450859L;

    @NotBlank
    @Size(max = 200)
    @Schema(description = "Candidate's name.", example = "Carlos César Gomes dos Santos Filho")
    private String name;

    @NotBlank
    @Size(max = 14)
    @CPF
    @Schema(description = "Candidate's CPF.", example = "11111111111")
    private String cpf;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Candidate's date of birth.", example = "1988-04-12")
    private LocalDate dateOfBirth;

    @NotBlank
    @Size(max = 200)
    @Email
    @Schema(description = "Candidate's e-mail.", example = "carloscesar.gsf@gmail.com")
    private String email;

    public String getName() {
        return name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf.trim();
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
        return email.trim();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CandidateCreateDTO{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                '}';
    }

}
