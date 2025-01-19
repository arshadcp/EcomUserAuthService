package dev.Arshad.EcomUserAuthService.Service;

import dev.Arshad.EcomUserAuthService.DTO.SignUpResponseDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserLoginRequestDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserResponseDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserSignupRequestDTO;
import dev.Arshad.EcomUserAuthService.Entity.Enum.SessionStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
   UserResponseDTO userLogin(UserLoginRequestDTO loginRequestDTO);
    //boolean userLogout(String token);
    String logout(String token, UUID userId);
    boolean signUp(UserSignupRequestDTO signupRequestDTO);
     SessionStatus validateToken(String token, UUID  userId);
    String createJWTtoken(UserLoginRequestDTO loginRequestDTO);

}
