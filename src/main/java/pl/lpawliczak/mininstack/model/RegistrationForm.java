package pl.lpawliczak.mininstack.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegistrationForm {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
}
