package dev.Arshad.EcomUserAuthService.DTO;

import dev.Arshad.EcomUserAuthService.Entity.Role;
import dev.Arshad.EcomUserAuthService.Entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    private String userName;
    private String email;
    private List<RoleResponseDTO> roles;

    public static UserResponseDTO convertUserToUserResponseDTO(User user){
        if(user==null){
            return null;
        }
        UserResponseDTO userresponseDTO=new UserResponseDTO();
        userresponseDTO.setUserName(user.getName());
        userresponseDTO.setEmail(user.getEmail());
        ArrayList<RoleResponseDTO> savedRoles=new ArrayList<>();
        for(Role roles: user.getRoles()){
           RoleResponseDTO roleDto= RoleResponseDTO.convertRoleToRoleResponseDTO(roles);
           savedRoles.add(roleDto);
        }
        userresponseDTO.setRoles(savedRoles);
         return userresponseDTO;
    }
    public static User converUserResponseDTOToUser(UserResponseDTO userResponseDTO){
        User user=new User();
        user.setName(userResponseDTO.getUserName());
        user.setEmail(userResponseDTO.getEmail());
        return user;
    }

}
