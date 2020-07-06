package br.com.carloscesargsf.candidatecase.dtos;

import br.com.carloscesargsf.candidatecase.validators.CandidateForRelationValidations;
import br.com.carloscesargsf.candidatecase.validators.CreditCardTypeForRelationValidations;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.*;
import java.io.Serializable;

public class CandidateCreditCardCreateDTO implements Serializable {

    private static final long serialVersionUID = 7855545961201330651L;
    
    @CreditCardTypeForRelationValidations
    @Schema(description = "Credit card type's ID.", example = "1")
    private Long creditCardTypeId;

    @NotBlank
    @Size(max = 200)
    @Schema(description = "Name written on the credit card.", example = "CARLOS CESAR G F")
    private String name;

    @NotBlank
    @Size(max = 20)
    @CreditCardNumber
    @Schema(description = "Credit card's number.", example = "1111111111111111")
    private String number;

    @NotNull
    @Min(2020)
    @Schema(description = "Credit card's year of expiration.", example = "2020")
    private Integer expiresOnYear;

    @NotNull
    @Min(1)
    @Max(12)
    @Schema(description = "Credit card's month of expiration.", example = "7")
    private Integer expiresOnMonth;

    public Long getCreditCardTypeId() {
        return creditCardTypeId;
    }

    public void setCreditCardTypeId(Long creditCardTypeId) {
        this.creditCardTypeId = creditCardTypeId;
    }

    public String getName() {
        return name.trim();
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

    public Integer getExpiresOnYear() {
        return expiresOnYear;
    }

    public void setExpiresOnYear(Integer expiresOnYear) {
        this.expiresOnYear = expiresOnYear;
    }

    public Integer getExpiresOnMonth() {
        return expiresOnMonth;
    }

    public void setExpiresOnMonth(Integer expiresOnMonth) {
        this.expiresOnMonth = expiresOnMonth;
    }

    @Override
    public String toString() {
        return "CreditCardCreateDTO{" +
                ", creditCardTypeId=" + creditCardTypeId +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", expiresOnYear=" + expiresOnYear +
                ", expiresOnMonth=" + expiresOnMonth +
                '}';
    }

}
