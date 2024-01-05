package com.mftplus.automation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@Entity(name = "generatedSequenceEntity")
@Table(name = "generated_sequence_tbl")
public class GeneratedSequence implements Serializable {
    @Id
    @SequenceGenerator(name = "generatedSeq", sequenceName = "generated_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "generatedSeq")
    private long id;


//    //to use id as letterNumber with a year prefix
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
//    @GenericGenerator(
//            name = "book_seq",
//            strategy = "org.thoughts.on.java.generators.DatePrefixedSequenceIdGenerator",
//            parameters = { @org.hibernate.annotations.Parameter(name = DatePrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50")})
//    private String id;

}
