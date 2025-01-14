package dev.Arshad.EcomUserAuthService.Service;

import dev.Arshad.EcomUserAuthService.DTO.CreateRoleRequestDTO;
import dev.Arshad.EcomUserAuthService.DTO.RoleResponseDTO;

import java.util.List;

public interface RoleService {
    RoleResponseDTO createRole(CreateRoleRequestDTO requestDTO);
    List<RoleResponseDTO> getAllRoles();
}
