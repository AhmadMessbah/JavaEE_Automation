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

@Entity(name = "stuffTransactionEntity")
@Table(name = "stuff_transaction_tbl")
public class StuffTransaction {
    @Id
    @SequenceGenerator(name = "stuffTransactionSeq", sequenceName = "stuff_transaction_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stuffTransactionSeq")
    private Long id;

    @OneToOne(cascade =  CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Stuff stuff;
}


