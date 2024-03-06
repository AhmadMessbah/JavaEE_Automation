package com.mftplus.automation.model;

import jakarta.enterprise.context.RequestScoped;
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

@Entity(name = "attachEntity")
@Table(name = "attach_tbl")
@RequestScoped
public class Attach extends Base implements Serializable {
    @Id
    @SequenceGenerator(name = "attachSeq", sequenceName = "attach_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachSeq")
    @Column (name = "a_id")
    private Long id;

    @Column (name = "a_title" , length = 25)
    private String title;
}


