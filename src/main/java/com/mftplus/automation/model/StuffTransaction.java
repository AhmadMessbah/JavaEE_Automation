package com.mftplus.automation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

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

    @Column(name = "ss_enteryStuffDate")
    private LocalDate enteryStuffDate;

    @Column(name = "ss_exitStuffDate")
    private LocalDate exitStuffDate;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "ss_enteredBy")
    private User enteredBy;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "ss_exitedBy")
    private User exitedBy;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "ss_section")
    private Section section;


    //todo: insert invoice and invoiceItem in entity
//    @OneToOne
//    @Column(name = "ss_saleDate")
//    private LocalDate saleDate;

//    //todo: insert invoice and invoiceItem in entity
//    @OneToOne(cascade = CascadeType.ALL)
//    @Column(name = "ss_purchaseInvoiceId")
//    private List<Invoice> purchaseInvoiceId;
//
//    //todo: insert invoice and invoiceItem in entity
//    @OneToOne
//    @Column(name = "ss_salesInvoiceId")
//    private List<Invoice> salesInvoiceId;
}


