package com.mftplus.automation.model;

import com.github.mfathi91.time.PersianDateTime;
import com.google.gson.Gson;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "checkTransactionEntity")
@Table(name = "check-transaction_tbl")

public class CheckPayment extends Payment{
    @Id
    @SequenceGenerator(name = "{name}Seq", sequenceName = "{name}_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "{name}Seq")
    @Column(name = "checkTransaction_id",length = 20)
    private Long id;

    @Pattern(regexp = "^{6}$",message = "Invalid Check Number")
    @Column(name = "checkTransaction_checkNumber",length = 6,unique = true)
    private String checkNumber;// شماره چک

    @Column(name ="checkTransaction_checkDueDate")
    private LocalDateTime checkDueDate;//تاریخ سررسید چک

    @ManyToOne(cascade = CascadeType.PERSIST)
    private CashDesk cashDesk;// صندوق

    @Transient
    private LocalDateTime faCheckDueDate;

    public String getFaCheckDueDate() {
        return PersianDateTime.fromGregorian(checkDueDate).toString();
    }

    public void setFaCheckDueDate(String faCheckDueDate) {
        this.checkDueDate = PersianDateTime.parse(faCheckDueDate).toGregorian();
    }

    @Override
    public String toString() {
        Gson gson=new Gson();
        return gson.toJson(this);
    }
}
