package dev.Arshad.EcomUserAuthService.DTO;

import dev.Arshad.EcomUserAuthService.Entity.Role;
import dev.Arshad.EcomUserAuthService.Entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserResponseDTO {
    private UUID userId;
    private String userName;
    private String email;
    private List<RoleResponseDTO> roles;
  //  private String token;

    public static UserResponseDTO convertUserToUserResponseDTO(User user){
        if(user==null){
            return null;
        }
        UserResponseDTO userresponseDTO=new UserResponseDTO();
        userresponseDTO.setUserName(user.getName());
        userresponseDTO.setEmail(user.getEmail());
        userresponseDTO.setUserId(user.getId());
        ArrayList<RoleResponseDTO> savedRoles=new ArrayList<>();
        //userresponseDTO.setToken(user.getToken());
        //String token=user.getToken();
        for(Role roles: user.getRoles()){
           RoleResponseDTO roleDto= RoleResponseDTO.convertRoleToRoleResponseDTO(roles);
           savedRoles.add(roleDto);
        }
        userresponseDTO.setRoles(savedRoles);
         return userresponseDTO;
    }


    public static User convertUserRespsonseDTOToUser(UserResponseDTO userResponseDTO){
        User user=new User();
        user.setName(userResponseDTO.getUserName());
        user.setEmail(userResponseDTO.getEmail());
        return user;
    }

}
