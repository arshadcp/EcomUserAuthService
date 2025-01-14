package dev.Arshad.EcomUserAuthService.Controller;

import dev.Arshad.EcomUserAuthService.DTO.UserLoginRequestDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserLogoutRequestDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserResponseDTO;
import dev.Arshad.EcomUserAuthService.DTO.UserSignupRequestDTO;
import dev.Arshad.EcomUserAuthService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
     @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO){
        return ResponseEntity.ok(userService.userLogin(userLoginRequestDTO));
    }
    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader ("Authorisation") String token){
        return ResponseEntity.ok(userService.userLogout(token));
    }
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserSignupRequestDTO signupRequestDTO){
        return ResponseEntity.ok(userService.signUp(signupRequestDTO));
    }



    @GetMapping("/validate")
    public  ResponseEntity<Boolean> validate(@RequestHeader ("Authorisation") String token ){
        return ResponseEntity.ok(userService.validateToken(token));
    }




}
