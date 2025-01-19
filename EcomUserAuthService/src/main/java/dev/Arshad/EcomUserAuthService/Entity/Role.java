package dev.Arshad.EcomUserAuthService.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends BaseModel{
    private String rolename;
    private String description;
}
