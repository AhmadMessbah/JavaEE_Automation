package com.mftplus.automation.model;

import com.mftplus.automation.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "financialTransactionEntity")
@Table(name = "financial_transaction_tbl")
public class FinancialTransaction {
    @Id
    @SequenceGenerator(name = "financialTransactionSeq", sequenceName = "financial_transaction_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialTransactionSeq")
    private Long id;

    private int trackingCode;// کد تراکنش

    private Long amount;// مقدار پول معامله شده

    private String description;//توضیحات

    @OneToOne(cascade = CascadeType.ALL)
    private Bank bankInvolved;//حساب درگیر

    private LocalDateTime dateTime;//تاریخ

    @Enumerated(EnumType.ORDINAL)
    private TransactionType  transactionType;//نوع تراکنش

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private FinancialDoc financialDoc;

}