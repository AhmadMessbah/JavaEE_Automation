package com.mftplus.automation.model;

import com.google.gson.Gson;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "bankDepositTransactionEntity")
@Table(name = "bank-deposit_transaction_tbl")
public class BankDepositTransaction extends FinancialTransaction{
    @Id
    @SequenceGenerator(name = "bankDepositTransactionSeq", sequenceName = "bank-deposit_transaction_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankDepositTransactionSeq")
    @Column(name = "bankDepositTransaction_id",length = 20)
    private Long id;

    @Pattern(regexp = "^{1,20}$",message = "Invalid Deposit Code")
    @Column(name = "bankDepositTransaction_depositCode",length = 20)
    private String depositCode; // کد واریز

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Bank bankInvolved; //  حساب بانک درگیر

    @Override
    public String toString() {
        Gson gson=new Gson();
        return gson.toJson(this);
    }
}
