package dev.Arshad.EcomUserAuthService.Controller;

import dev.Arshad.EcomUserAuthService.DTO.*;
import dev.Arshad.EcomUserAuthService.Entity.Enum.SessionStatus;
import dev.Arshad.EcomUserAuthService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
     @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO){
        UserResponseDTO responseDTO=userService.userLogin(userLoginRequestDTO);
        MultiValueMap<String,String> header=new LinkedMultiValueMap<>();
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

        String token= userService.createJWTtoken(userLoginRequestDTO);
        header.add("Auth_Token",token);
        ResponseEntity<UserResponseDTO> response=new ResponseEntity<>(responseDTO,header, HttpStatus.OK);

        return response;

    }

    @GetMapping("/logout/{id}")
    public ResponseEntity<String> logout(@RequestHeader ("Authorisation") String token,@PathVariable("id") UUID userId){
        return ResponseEntity.ok(userService.logout(token,userId));


    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> login(@RequestBody UserSignupRequestDTO signupRequestDTO){
        return ResponseEntity.ok(userService.signUp(signupRequestDTO));
    }





@PostMapping("/validate")
  public ResponseEntity<SessionStatus> validateToken(@RequestBody ValidateTokenRequestDto request) {
        SessionStatus sessionStatus = userService.validateToken(request.getToken(), request.getUserId());

        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    }



}
