package com.mftplus.model;

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

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@Entity(name = "rolesEntity")
@Table(name = "roles_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = {"u_username","role_name"})})
public class Roles extends Base implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq")
    @SequenceGenerator(name = "roles_seq", sequenceName = "roles_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "u_username")
    private User user;

    @Column(name = "role_name", columnDefinition = "NVARCHAR2(10)")
    @Pattern(regexp = "^[a-zA-Z\\s]{4,10}$", message = "Invalid RoleName")
    @Size(min = 4, max = 10, message = "RoleName must be between 4 and 10 characters")
    @NotBlank(message = "Should Not Be Null")
    private String role;
}