package dev.Arshad.EcomUserAuthService.Controller;

import dev.Arshad.EcomUserAuthService.DTO.UserLoginRequestDTO;
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

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserSignupRequestDTO userSignupRequestDTO){
        return ResponseEntity.ok(userService.createUser(userSignupRequestDTO));
    }
    @GetMapping
    public ResponseEntity getAllusers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable ("id") UUID id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable ("id") UUID id){
        return ResponseEntity.ok(userService.deleteUserById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity updateUserById(@PathVariable ("id") UUID id,@RequestBody UserLoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(userService.updateUserById(id,loginRequestDTO));
    }


}
