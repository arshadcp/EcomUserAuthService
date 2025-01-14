package dev.Arshad.EcomUserAuthService.Controller;

import dev.Arshad.EcomUserAuthService.DTO.CreateRoleRequestDTO;
import dev.Arshad.EcomUserAuthService.DTO.RoleResponseDTO;
import dev.Arshad.EcomUserAuthService.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
  private  RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody CreateRoleRequestDTO createRoleRequestDTO){
        return ResponseEntity.ok(roleService.createRole(createRoleRequestDTO));
    }
    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
