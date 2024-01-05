package com.mftplus.automation.model;

import jakarta.persistence.*;
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

@Entity(name = "organisationEntity")
@Table(name = "organisation_tbl")
public class Organisation {
    @Id
    @SequenceGenerator(name = "organisationSeq", sequenceName = "organisation_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organisationSeq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "o_title" , length = 40)
    private String title;

    @OneToOne
    private Attach logo;

    @Column(name = "o_address" , length = 100)
    private String address;


    @Column(name = "o_phoneNnumber" , length = 11)
    private String PhoneNumber;

    @Column(name = "o_description")
    private String description;


    @OneToMany
    private List<Section> sectionList;

    public void addSection(Section section){
        if (sectionList==null){
            sectionList=new ArrayList<>();
        }
    }

}
