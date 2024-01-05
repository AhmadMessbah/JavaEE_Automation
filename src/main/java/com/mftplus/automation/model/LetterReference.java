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

@Entity(name = "letterReferenceEntity")
@Table(name = "letter_reference_tbl")
public class LetterReference {
    @Id
    @SequenceGenerator(name = "letterReferenceSeq", sequenceName = "letter_reference_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "letterReferenceSeq")
    private Long id;

    private String title;
    
    @OneToOne(cascade = CascadeType.ALL)
    private User sender;

    @OneToOne(cascade = CascadeType.ALL)
    private User receiver;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Attach attach;
}


