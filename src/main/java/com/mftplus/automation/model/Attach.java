package com.mftplus.automation.model;

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

@Entity(name = "attachEntity")
@Table(name = "attach_tbl")
public class Attach {
    @Id
    @SequenceGenerator(name = "attachSeq", sequenceName = "attach_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachSeq")
    private Long id;

    private String title;
}


