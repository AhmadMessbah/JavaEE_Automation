package com.mftplus.automation.model;

import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "cashTransactionEntity")
@Table(name = "cash_transaction_tbl")
public class CashTransaction extends FinancialTransaction{
    @Id
    @SequenceGenerator(name = "bankDepositTransactionSeq", sequenceName = "bank-deposit_transaction_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankDepositTransactionSeq")
    @Column(name = "cashTransaction_id",length = 20)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private CashDesk cashDesk;// صندوق

    @Override
    public String toString() {
        Gson gson=new Gson();
        return gson.toJson(this);
    }
}
