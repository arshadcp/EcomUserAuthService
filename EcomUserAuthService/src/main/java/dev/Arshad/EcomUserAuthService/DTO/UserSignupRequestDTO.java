package dev.Arshad.EcomUserAuthService.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDTO {
    private String userName;
    private String email;
    private String password;
}
