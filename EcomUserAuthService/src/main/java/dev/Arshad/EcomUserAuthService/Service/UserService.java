package dev.Arshad.EcomUserAuthService.Service;

import dev.Arshad.EcomUserAuthService.DTO.SignUpResponseDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserLoginRequestDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserResponseDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserSignupRequestDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
   UserResponseDTO userLogin(UserLoginRequestDTO loginRequestDTO);
    boolean userLogout(String token);
    boolean signUp(UserSignupRequestDTO signupRequestDTO);
     boolean validateToken(String token);
    String createJWTtoken(UserLoginRequestDTO loginRequestDTO);

}
