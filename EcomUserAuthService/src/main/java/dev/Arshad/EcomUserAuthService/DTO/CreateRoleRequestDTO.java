package dev.Arshad.EcomUserAuthService.DTO;

import dev.Arshad.EcomUserAuthService.Entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoleRequestDTO {
    private String name;
    private String description;

    public static Role convertCreateRoleRequestDTOToRole(CreateRoleRequestDTO createRoleRequestDTO){
        Role role=new Role();
        role.setRole(createRoleRequestDTO.getName());
        role.setDescription(createRoleRequestDTO.getDescription());
        return role;
    }
}
