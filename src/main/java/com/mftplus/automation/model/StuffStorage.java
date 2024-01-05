package com.mftplus.automation.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "StuffStorageEntity")
@Table(name = "stuffStorage_tbl")


public class StuffStorage {
    @Id
    @SequenceGenerator(name = "stuffStorageSeq", sequenceName = "stuffStorage_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ss_id", nullable = false)
    private int id;


    @Column(name = "ss_name", length = 20,nullable = false)
    private String name;

    @Column(name = "ss_count", length = 20,nullable = false)
    private int count;

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

