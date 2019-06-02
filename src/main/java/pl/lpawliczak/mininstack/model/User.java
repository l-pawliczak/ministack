package pl.lpawliczak.mininstack.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String username;
    private String password;
    private String email;
    private Boolean authorized;
    private Date created;
}
