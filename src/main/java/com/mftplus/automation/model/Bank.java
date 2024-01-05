package com.mftplus.automation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "bankEntity")
@Table(name = "bank_tbl")
public class  Bank {
    @Id
    @SequenceGenerator(name = "bankSeq", sequenceName = "bank_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankSeq")
    private Long id;

    private String name;// نام بانک

    private String accountNumber;// شماره حساب

    private int branchCode;// کد شعبه

    private String branchName;// نام شعبه

    private String accountType;// نوع حساب بانکی

    private Long accountBalance;//  موجودی حساب

    @OneToOne(cascade = CascadeType.ALL)
    private User accountOwner;//صاحب حساب

    @OneToMany(cascade = CascadeType.ALL)
    private List<FinancialTransaction> financialTransactionList;

    public void addFinancialTransaction(FinancialTransaction financialTransaction){
        if (financialTransactionList==null){
            financialTransactionList=new ArrayList<>();
        }
        financialTransactionList.add(financialTransaction);
    }

}


