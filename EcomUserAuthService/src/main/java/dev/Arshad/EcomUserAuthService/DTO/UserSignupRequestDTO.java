package dev.Arshad.EcomUserAuthService.DTO;

import dev.Arshad.EcomUserAuthService.Entity.User;
import dev.Arshad.EcomUserAuthService.Exception.RoleNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Getter
@Setter
public class UserSignupRequestDTO {
    private String name;
    private String email;
    private String password;
    private UUID roleId;

    public static User convertUserSignupRequestDTOToUser(UserSignupRequestDTO signupRequestDTO) throws RoleNotFoundException {
        User user=new User();
        user.setName(signupRequestDTO.getName());
        user.setEmail(signupRequestDTO.getEmail());
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(signupRequestDTO.getPassword()));
        return user;

    }
}
/*
    name
    email
    password
    roleId
 */