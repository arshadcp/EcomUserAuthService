package dev.Arshad.EcomUserAuthService.Service;

import dev.Arshad.EcomUserAuthService.DTO.CreateRoleRequestDTO;
import dev.Arshad.EcomUserAuthService.DTO.RoleResponseDTO;
import dev.Arshad.EcomUserAuthService.Entity.Role;
import dev.Arshad.EcomUserAuthService.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository roleRepository;
   public RoleResponseDTO createRole(CreateRoleRequestDTO requestDTO){
       Role role= CreateRoleRequestDTO.convertCreateRoleRequestDTOToRole(requestDTO);
       Role savedRole=roleRepository.save(role);
       return RoleResponseDTO.convertRoleToRoleResponseDTO(savedRole);
   }

   public List<RoleResponseDTO> getAllRoles(){
       List<Role> savedroles=roleRepository.findAll();
       List<RoleResponseDTO> responseDTO=new ArrayList<>();
       for(Role role:savedroles ){
         responseDTO.add(RoleResponseDTO.convertRoleToRoleResponseDTO(role));
       }

       return responseDTO;
   }

}
