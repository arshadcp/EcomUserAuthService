package dev.Arshad.EcomUserAuthService.DTO;

import dev.Arshad.EcomUserAuthService.Entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RoleResponseDTO {
    private String role;
    private  String description;
    private UUID roleId;

    public  static RoleResponseDTO convertRoleToRoleResponseDTO(Role role){
        RoleResponseDTO responseDTO=new RoleResponseDTO();
        responseDTO.setRole(role.getRolename());
        responseDTO.setDescription(role.getDescription());
        responseDTO.setRoleId(role.getId());
         return responseDTO;
    }
}
