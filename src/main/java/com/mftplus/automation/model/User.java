package com.mftplus.automation.model;
import com.mftplus.automation.model.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Pattern(regexp = "^[a-zA-Z\\s]{5,15}$", message = "Invalid Username")
    @Column(name = "u_name", length = 15)
    private String username;

    @Pattern(regexp = "^[a-zA-Z\\s]{8,20}$", message = "Invalid Password")
    @Column(name = "u_pass", length = 20)
    private String password;

//should it be list?
    @OneToOne(cascade = CascadeType.ALL)
    private Person personId;
/*
   // todo: error
    @OneToMany(cascade = CascadeType.ALL)
    private List<Section> sectionIdList;
    public void addSectionId(Section section) {
        if (sectionIdList == null) {
            sectionIdList = new ArrayList<>();
        }
        sectionIdList.add(section);
    }*/

    @Column(name="u_active")
    private boolean active;

    @Enumerated(EnumType.ORDINAL)
    private Roles role;
}
