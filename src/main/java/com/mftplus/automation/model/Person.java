package com.mftplus.automation.model;

import com.mftplus.automation.model.enums.Gender;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
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
@ToString

@Entity(name = "personEntity")
@Table(name = "person_tbl")
@RequestScoped
public class Person extends Base implements Serializable {
    @Id
    //allocation size for fixing a bug
    @SequenceGenerator(name = "personSeq", sequenceName = "person_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
    @Column(name = "p_id")
    private Long id;

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,30}$", message = "Invalid Name")
    @Column(name = "p_name", length = 30)
    private String name;

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,30}$", message = "Invalid Family")
    @Column(name = "p_family", length = 30)
    private String family;

    @Column(name = "p_nationalCode", length = 10)
    private String nationalCode;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

}
