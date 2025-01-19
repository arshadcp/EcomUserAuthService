package dev.Arshad.EcomUserAuthService.Service;


import dev.Arshad.EcomUserAuthService.DTO.UserLoginRequestDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserResponseDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserSignupRequestDTO;
import dev.Arshad.EcomUserAuthService.Entity.Enum.SessionStatus;
import dev.Arshad.EcomUserAuthService.Entity.Role;
import dev.Arshad.EcomUserAuthService.Entity.Session;
import dev.Arshad.EcomUserAuthService.Entity.User;
import dev.Arshad.EcomUserAuthService.Exception.*;
import dev.Arshad.EcomUserAuthService.Repository.RoleRepository;
import dev.Arshad.EcomUserAuthService.Repository.SessionRepository;
import dev.Arshad.EcomUserAuthService.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
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




//    @Override
//    public boolean userLogout(String token) {
//        Session savedSession=sessionRepository.findByToken(token).orElseThrow(
//                ()-> new invalidCredentialException("Token is not valid")
//        );
//        savedSession.setSessionStatus(SessionStatus.ENDED);
//        savedSession.setToken(null);
//        sessionRepository.save(savedSession);
//        return true;

    public String logout(String token, UUID userId) {
        // validations -> token exists, token is not expired, user exists else throw an exception
      Session session = sessionRepository.findByTokenAndUser_Id(token, userId).orElseThrow(
              ()-> new invalidSessionException("Session not found")
      );


        session.setSessionStatus(SessionStatus.ENDED);
        session.setToken(null);
        sessionRepository.save(session);
        String msg="Session Ended";
        return msg;
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


public SessionStatus validateToken(String token,UUID  userId) {
//    Jws<Claims> claims=Jwts.parser()
//
//               .verifyWith() // <----
//
//               .build()
//               .parseSignedClaims(token);


    Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);
    if (sessionOptional.isEmpty() || sessionOptional.get().getSessionStatus().equals(SessionStatus.ENDED)) {
        throw new invalidTokenException("token is invalid");
    }
    return SessionStatus.ACTIVE;
}
   public   String createJWTtoken(UserLoginRequestDTO loginRequestDTO){
        User savedUser=userRepository.findUserByEmail(loginRequestDTO.getEmail()).get();

       Map<String, Object> dataInJWT= new HashMap<>();
       dataInJWT.put("User_id",savedUser.getId());
       List<String> roles=new ArrayList<>();
       for(Role role:savedUser.getRoles()){
           roles.add(role.getRolename());
       }
       dataInJWT.put("Roles",roles);
      // dataInJWT.put("Email",savedUser.getEmail());
       dataInJWT.put("createdAt",new Date());
     //  dataInJWT.put("expiry", new Date(LocalDateTime.now().plusDays(3).toEpoc()));
       String jws = Jwts.builder().claims(dataInJWT)
              // .subject("This is the gateway")
              // .issuer("Arshad")
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
