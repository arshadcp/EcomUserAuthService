package dev.Arshad.EcomUserAuthService.Service;

import dev.Arshad.EcomUserAuthService.DTO.UserLoginRequestDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserResponseDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserSignupRequestDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
   UserResponseDTO createUser(UserSignupRequestDTO userSignupRequestDTO);

    UserResponseDTO getUserById(UUID userId);

    List<UserResponseDTO> getAllUsers();


    boolean deleteUserById(UUID userId);

   UserResponseDTO updateUserById(UUID id, UserLoginRequestDTO loginRequestDTO);


}
