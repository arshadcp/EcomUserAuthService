package dev.Arshad.EcomUserAuthService.DTO;

import dev.Arshad.EcomUserAuthService.Entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDTO {
    private String role;
    private  String description;

    public  static RoleResponseDTO convertRoleToRoleResponseDTO(Role role){
        RoleResponseDTO responseDTO=new RoleResponseDTO();
        responseDTO.setRole(role.getRole());
        responseDTO.setDescription(role.getDescription());
         return responseDTO;
    }
}
