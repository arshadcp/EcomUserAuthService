package dev.Arshad.EcomUserAuthService.Service;

import dev.Arshad.EcomUserAuthService.DTO.*;
import dev.Arshad.EcomUserAuthService.Entity.Enum.SessionStatus;
import dev.Arshad.EcomUserAuthService.Entity.Role;
import dev.Arshad.EcomUserAuthService.Entity.Session;
import dev.Arshad.EcomUserAuthService.Entity.User;
import dev.Arshad.EcomUserAuthService.Exception.RoleNotFoundException;
import dev.Arshad.EcomUserAuthService.Exception.UserAlreadyExistEXception;
import dev.Arshad.EcomUserAuthService.Exception.UserNotFoundException;
import dev.Arshad.EcomUserAuthService.Exception.invalidCredentialException;
import dev.Arshad.EcomUserAuthService.Repository.RoleRepository;
import dev.Arshad.EcomUserAuthService.Repository.SessionRepository;
import dev.Arshad.EcomUserAuthService.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private SessionRepository sessionRepository;
    private SecretKey key= Jwts.SIG.HS256.key().build();

    @Override
    public UserResponseDTO userLogin(UserLoginRequestDTO loginRequestDTO) {

        User savedUser=userRepository.findUserByEmail(loginRequestDTO.getEmail()).orElseThrow(
                ()->new UserNotFoundException("User not found with the given email")
        );
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        if(encoder.matches(loginRequestDTO.getPassword(), savedUser.getPassword())){
            return UserResponseDTO.convertUserToUserResponseDTO(savedUser);
        }
        else{
            throw new invalidCredentialException("Wrong Password");
        }




    }




    @Override
    public boolean userLogout(String token) {
//        User savedUser=userRepository.findByToken(token).orElseThrow(
//                ()-> new invalidCredentialException("Token is not valid")
//        );
//
//        userRepository.save(savedUser);
        return true;
    }

    @Override
    public boolean signUp(UserSignupRequestDTO signupRequestDTO) {
        if(userRepository.findUserByEmail(signupRequestDTO.getEmail()).isPresent()){
            throw  new UserAlreadyExistEXception("User already exist");
        }
            Role role=roleRepository.findById(signupRequestDTO.getRoleId()).orElseThrow(
                    ()-> new RoleNotFoundException("Roles with given id not found")
            );

            User user=UserSignupRequestDTO.convertUserSignupRequestDTOToUser(signupRequestDTO);
            user.setRoles(List.of(role));

            userRepository.save(user);
            return true;

    }

   public boolean validateToken(String token){
        Jws<Claims> claims=Jwts.parser()

               .verifyWith(key) // <----

               .build()
               .parseSignedClaims(token);
        return true;
   }
   public   String createJWTtoken(UserLoginRequestDTO loginRequestDTO){
        User savedUser=userRepository.findUserByEmail(loginRequestDTO.getEmail()).get();

       Map<String, Object> dataInJWT= new HashMap<>();
       dataInJWT.put("User_id",savedUser.getId());

       dataInJWT.put("Roles",savedUser.getRoles());
       dataInJWT.put("Email",savedUser.getEmail());
       String jws = Jwts.builder().claims(dataInJWT)
               .subject("Joe")
               .issuer("Arshad")
               .signWith(key)
               .compact();
//
       Session session=new Session();
       session.setToken(jws);
       session.setUser(savedUser);
       session.setSessionStatus(SessionStatus.ACTIVE);
      sessionRepository.save(session);
       return jws;
   }
}
//
