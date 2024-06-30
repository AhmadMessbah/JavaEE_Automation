package com.mftplus.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString(exclude = {"roleList", "department"})

@Entity(name = "userEntity")
@Table(name = "user_tbl")
@RequestScoped
public class User extends Base implements Serializable {
    @Id
    @Column(name = "u_username", columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Name")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String username;

    @JsonbTransient
    @Column(name = "u_password", columnDefinition = "NVARCHAR2(20)", nullable = false)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,20}$", message = "Minimum five characters, at least one letter and one number!")
    @Size(min = 5, max = 20, message = "Password must be between 5 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String password;

    @Column(name = "u_active")
    private boolean active;

    @JsonbTransient
    @OneToMany(fetch = FetchType.EAGER)
    private List<Roles> roleList;

    public void addRole(Roles role) {
        if (roleList == null) {
            roleList = new ArrayList<>();
        }
        roleList.add(role);
    }

    @JsonbTransient
    @ManyToOne
    private Department department;
}
