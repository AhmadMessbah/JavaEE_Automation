package com.mftplus.automation.model;

import com.mftplus.automation.model.enums.FinancialDocType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "financialDocEntity")
@Table(name = "financial_doc_tbl")
public class FinancialDoc {
    @Id
    @SequenceGenerator(name = "financialDocSeq", sequenceName = "financial_doc_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialDocSeq")
    private Long id;//شماره عطف

    private Long numberDoc;//شماره سند

    private LocalDateTime dateTime;//تاریخ

    private int creditor; //بستانکار

    private int creditorSum;//جمع بستانکار

    private int debtor; // بدهکار

    private int debtorSum;//جمع بدهکار

    private int difference;//اختلاف

    private String description;//توضیحات

    @Enumerated(EnumType.ORDINAL)
    private FinancialDocType type;//نوع

    @OneToOne(cascade = CascadeType.ALL)
    private Section section;

    @OneToMany(cascade = CascadeType.ALL)
    private List<FinancialTransaction> financialTransactionList;

    @OneToOne(cascade = CascadeType.ALL)
    private User sender;// فرستنده

    @OneToOne(cascade = CascadeType.ALL)
    private User receiver;// گیرنده

    public void addFinancialTransaction(FinancialTransaction financialTransaction){
        if (financialTransactionList==null){
            financialTransactionList=new ArrayList<>();
        }
        financialTransactionList.add(financialTransaction);
    }
}


