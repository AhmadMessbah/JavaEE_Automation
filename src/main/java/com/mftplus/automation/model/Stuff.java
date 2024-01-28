package com.mftplus.automation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "stuffEntity")
@Table(name = "stuff_tbl")
public class Stuff implements Serializable {
    @Id
    @SequenceGenerator(name = "stuffSeq", sequenceName = "stuff_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stuffSeq")
    private Long id;

    @Column(name = "stuff_name", length = 20,nullable = false)
    private String name;

    @Column(name = "stuff_brand", length = 20)
    private String brand;

    @Column(name = "stuff_price", length = 20,nullable = false)
    private Long price;

    @Column(name = "stuff_model", length = 20)
    private String model;


    @Column(name = "stuff_status", length = 20)
    private String status;



    @ManyToOne(cascade = CascadeType.ALL)
    private Section section;


    @ManyToOne(cascade = CascadeType.ALL)
    private User user;


    @OneToOne(cascade = CascadeType.ALL)
    private Attach attach;
}


