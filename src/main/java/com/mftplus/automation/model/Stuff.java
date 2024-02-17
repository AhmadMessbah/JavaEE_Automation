package com.mftplus.automation.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
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

@Entity(name = "stuffEntity")
@Table(name = "stuff_tbl")
@RequestScoped
public class Stuff extends Base {
    @Id
    @SequenceGenerator(name = "stuffSeq", sequenceName = "stuff_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stuffSeq")
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "stuff_name", length = 20)
    private String name;

    @Column(name = "stuff_brand", length = 20)
    private String brand;

    @Column(name = "stuff_price", length = 20)
    private String price;

    @Column(name = "stuff_model", length = 20)
    private String model;


    @Column(name = "stuff_status", length = 20)
    private String status;


//    @ManyToOne
//    private Section section;
//
//    @ManyToOne
//    private User users;


//    @OneToOne
//    private Attach attach;

}


