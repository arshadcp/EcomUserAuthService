package dev.Arshad.EcomUserAuthService.Entity;

import dev.Arshad.EcomUserAuthService.Entity.Enum.SessionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table
public class Session extends BaseModel{
    private String token;
    private Date expiringAt;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private SessionStatus sessionStatus;
}
//@Column(columnDefinition = "varchar(255)")
