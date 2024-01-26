package com.mftplus.automation.model;

import jakarta.json.bind.annotation.JsonbTransient;
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

@Entity(name = "sectionEntity")
@Table(name = "section_tbl")
public class Section {
    @Id
    @SequenceGenerator(name = "sectionSeq", sequenceName = "section_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sectionSeq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 1)
    @JsonbTransient
    private Boolean deleted;

    @Column(name = "s_title", length = 40)
    @Pattern(regexp = "^[a-zA-Z\\s]{0,40}$", message = "Invalid Role")
    private String title;

    @ManyToOne
    private Organisation organisation;

    @Column(name = "s_duty", length = 100)
    private String duty;

    @Column(name = "s_phoneNnumber", length = 11)
    private String phoneNumber;

    @OneToMany
    private List<User> users;

    @OneToMany
    private List<Section> sectionsPart;

    public List<Section> getSectionsPart() {
        if (sectionsPart == null) {
            sectionsPart = new ArrayList<>();
        }
        return sectionsPart;
    }


    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    @OneToOne
    private Attach attach;


}
