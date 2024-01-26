package com.mftplus.automation.model;

import com.mftplus.automation.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "userEntity")
@Table(name = "user_tbl")
public class User extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Pattern(regexp = "^[a-zA-Z\\s]{5,15}$", message = "Invalid Username")
    @Column(name = "u_name", length = 15)
    private String username;

    @Pattern(regexp = "^[a-zA-Z\\s]{8,20}$", message = "Invalid Password")
    @Column(name = "u_pass", length = 20)
    private String password;

// todo
    @OneToMany
    private List<User> userList;
    public List<User> getUserList() {
        if (userList == null) {
            userList = new ArrayList<>();
        }
        return userList;
    }


    @OneToOne(cascade = CascadeType.ALL)
    private Person person;

    @ManyToOne
    private Section section;

    //todo
    @OneToOne(cascade = CascadeType.ALL)
    private Person personId;

    //todo
    @ManyToOne
    private Section sectionId;

    @Column(name="u_active")
    private boolean active;

    @Enumerated(EnumType.ORDINAL)
    private Role role;
}
