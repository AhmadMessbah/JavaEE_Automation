package com.mftplus.automation.model;

import com.mftplus.automation.model.enums.StuffTransactionType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "stuffTransactionEntity")
@Table(name = "stuff_transaction_tbl")
@RequestScoped
public class StuffTransaction {
    @Id
    @SequenceGenerator(name = "stuffTransactionSeq", sequenceName = "stuff_transaction_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stuffTransactionSeq")
    private Long id;

    @OneToOne(cascade =  CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Section section;

    @OneToOne(cascade = CascadeType.ALL)
    private Stuff stuff;

    @Column(name = "ss_dateTime")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.ORDINAL)
    private StuffTransactionType transactionType;
}


