package dev.Arshad.EcomUserAuthService.DTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

@Getter
@Setter
public class createRoleRequestDTO {
    private String name;
    private String description;
}
