package com.mftplus.automation.model;

import com.mftplus.automation.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "personEntity")
@Table(name = "person_tbl")
public class Person {
    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "person_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z\\s]{3,30}$", message = "Invalid Name")
    @Column(name = "p_name", length = 30)
    private String name;

    @Pattern(regexp = "^[a-zA-Z\\s]{3,30}$", message = "Invalid Family")
    @Column(name = "p_family", length = 30)
    private String family;


    @Column(name = "p_nationalCode", length = 10)
    private String nationalCode;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

}
