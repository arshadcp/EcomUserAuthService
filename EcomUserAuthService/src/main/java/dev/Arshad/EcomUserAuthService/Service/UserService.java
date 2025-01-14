package dev.Arshad.EcomUserAuthService.Service;

import dev.Arshad.EcomUserAuthService.DTO.UserLoginRequestDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserResponseDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserSignupRequestDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
   UserResponseDTO userLogin(UserLoginRequestDTO loginRequestDTO);
    boolean userLogout(String token);
    UserResponseDTO signUp(UserSignupRequestDTO signupRequestDTO);
     boolean validateToken(String token);


}
