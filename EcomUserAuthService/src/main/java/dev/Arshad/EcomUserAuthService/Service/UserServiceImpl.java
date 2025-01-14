package dev.Arshad.EcomUserAuthService.Service;

import dev.Arshad.EcomUserAuthService.DTO.RoleResponseDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserLoginRequestDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserResponseDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserSignupRequestDTO;
import dev.Arshad.EcomUserAuthService.Entity.Role;
import dev.Arshad.EcomUserAuthService.Entity.User;
import dev.Arshad.EcomUserAuthService.Exception.RoleNotFoundException;
import dev.Arshad.EcomUserAuthService.Exception.UserNotFoundException;
import dev.Arshad.EcomUserAuthService.Exception.invalidCredentialException;
import dev.Arshad.EcomUserAuthService.Repository.RoleRepository;
import dev.Arshad.EcomUserAuthService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserResponseDTO userLogin(UserLoginRequestDTO loginRequestDTO) {
        //User user=UserLoginRequestDTO.convertUserLoginRequestDTOToUser(loginRequestDTO);
        User savedUser=userRepository.findUserByEmail(loginRequestDTO.getEmail()).orElseThrow(
                ()->new UserNotFoundException("User not found with the given email")
        );
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        if(encoder.matches(loginRequestDTO.getPassword(), savedUser.getPassword())){
            String userData=savedUser.getEmail()+savedUser.getPassword()+ LocalDateTime.now();
            String token= encoder.encode(userData);
            savedUser.setToken(token);
        }
        else{
            throw new invalidCredentialException("Wrong Password");
        }
        savedUser=userRepository.save(savedUser);
        return UserResponseDTO.convertUserToUserResponseDTO(savedUser);


    }


    @Override
    public boolean userLogout(String token) {
        User savedUser=userRepository.findByToken(token).orElseThrow(
                ()-> new invalidCredentialException("Token is not valid")
        );
        savedUser.setToken(null);
        userRepository.save(savedUser);
        return true;
    }

    @Override
    public UserResponseDTO signUp(UserSignupRequestDTO signupRequestDTO) {
        Role role=roleRepository.findById(signupRequestDTO.getRoleId()).orElseThrow(
                ()-> new RoleNotFoundException("Roles with given id not found")
        );

        User user=UserSignupRequestDTO.convertUserSignupRequestDTOToUser(signupRequestDTO);
        user.setRoles(List.of(role));

        User savedUser=userRepository.save(user);
        return UserResponseDTO.convertUserToUserResponseDTO(savedUser);

    }

   public boolean validateToken(String token){
       User savedUser=userRepository.findByToken(token).orElseThrow(
               ()-> new invalidCredentialException("Token is not valid")
       );
       return true;
   }
}
