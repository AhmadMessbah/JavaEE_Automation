package com.mftplus.model;

import com.mftplus.model.enums.Gender;
import jakarta.enterprise.context.RequestScoped;
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
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@Entity(name = "personEntity")
@Table(name = "person_tbl")
@RequestScoped
public class Person extends Base implements Serializable {

    //todo : for all entities validation msg is in eng, we need to decide for the persian msg
    @Id
    @SequenceGenerator(name = "person_seq", sequenceName = "PERSON_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @Column(name = "p_id")
    private Long id;

    @Column(name = "p_name", columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Name")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String name;

    @Column(name = "p_family", columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Family")
    @Size(min = 3, max = 20, message = "Family must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String family;

    @Column(name = "p_nationalCode", columnDefinition = "NVARCHAR2(10)")
    @Pattern(regexp = "^[0-9]{1,10}$", message = "Invalid NationalCode")
    @Size(min = 1, max = 10, message = "NationalCode must be between 1 and 10 characters")
    @NotBlank(message = "Should Not Be Null")
    private String nationalCode;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, unique = true)
    private User user;

    //todo : has not been set in servlet or form
    @Column(name = "p_birthdate")
//    @Past(message = "Invalid Birthdate")
    private LocalDate birthdate;

    //todo : attachment
    @Column(name = "p_image")
    private String image;

}