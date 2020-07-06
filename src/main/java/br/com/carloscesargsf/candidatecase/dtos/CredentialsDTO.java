package br.com.carloscesargsf.candidatecase.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Schema(description = "Object used by user to authenticate on the API.")
public class CredentialsDTO implements Serializable {

    private static final long serialVersionUID = 3995037019550046663L;

    @Schema(description = "User's e-mail.", example = "admin@example.com")
    @NotBlank
    @Email
    private String username;

    @Schema(description = "User's password.", example = "123456")
    @NotBlank
    private String password;

    public CredentialsDTO() {
    }

    public String getUsername() {
        return username.trim();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password.trim();
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
