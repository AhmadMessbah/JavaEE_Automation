package com.mftplus.automation.model;

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

    @Column(name = "s_title" , length = 40)
    @Pattern(regexp = "^[a-zA-Z\\s]{0,40}$", message = "Invalid Role")
    private String title;

    @ManyToOne
    private Organisation organisation;

    @Column(name = "s_duty",length = 100)
    private String duty;

    @Column(name = "s_phoneNnumber" , length = 11)
    private String PhoneNumber;

    @OneToMany
    private List<User>users;

    @OneToMany
    private List<Stuff>stuffs;

    @OneToOne
    private Attach attach;

    public void addUser(User user){
        if (users==null){
            users=new ArrayList<>();
        }
    }
    public void addStuff(Stuff stuff){
        if (stuffs==null){
            stuffs=new ArrayList<>();
        }
    }

}
