package dev.Arshad.EcomUserAuthService.DTO;

import dev.Arshad.EcomUserAuthService.Entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDTO {
    private String email;
    private String password;

    public static User convertUserLoginRequestDTOToUser(UserLoginRequestDTO loginRequestDTO){
        User user=new User();
        user.setEmail(loginRequestDTO.getEmail());
        user.setPassword(loginRequestDTO.getPassword());
        return user;

    }
}
