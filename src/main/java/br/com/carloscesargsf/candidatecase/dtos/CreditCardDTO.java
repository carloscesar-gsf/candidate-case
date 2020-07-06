package br.com.carloscesargsf.candidatecase.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDate;

public class CreditCardDTO implements Serializable {

    private static final long serialVersionUID = 7855545961201330651L;

    @Schema(description = "Credit card's ID.", example = "1")
    private Long id;

    @Schema(description = "Candidate's ID.", example = "1")
    private Long candidateId;

    @Schema(description = "Credit card type's ID.", example = "1")
    private Long creditCardTypeId;

    @Schema(description = "Name written on the credit card.", example = "CARLOS CESAR G F")
    private String name;

    @Schema(description = "Credit card's number.", example = "1111111111111111")
    private String number;

    @JsonFormat(pattern = "yyyy-MM")
    @Schema(description = "Credit card's expiration date.", example = "2020-07")
    private LocalDate expiresOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getCreditCardTypeId() {
        return creditCardTypeId;
    }

    public void setCreditCardTypeId(Long creditCardTypeId) {
        this.creditCardTypeId = creditCardTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(LocalDate expiresOn) {
        this.expiresOn = expiresOn;
    }

    @Override
    public String toString() {
        return "CreditCardListItemDTO{" +
                "candidateId=" + candidateId +
                ", creditCardTypeId=" + creditCardTypeId +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", expiresOn=" + expiresOn +
                '}';
    }

}
