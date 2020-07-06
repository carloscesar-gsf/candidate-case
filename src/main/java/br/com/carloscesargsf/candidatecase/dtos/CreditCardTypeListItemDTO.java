package br.com.carloscesargsf.candidatecase.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class CreditCardTypeListItemDTO implements Serializable {

    private static final long serialVersionUID = -5197898114416087390L;

    @Schema(description = "Credit card type's ID.", example = "1")
    private Long id;

    @Schema(description = "Credit card type's name.", example = "MasterCard")
    private String name;

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

    @Override
    public String toString() {
        return "CreditCardTypeListDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
