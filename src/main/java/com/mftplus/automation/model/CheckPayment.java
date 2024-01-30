package com.mftplus.automation.model;

import com.github.mfathi91.time.PersianDateTime;
import com.google.gson.Gson;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
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

@Entity(name = "checkPaymentEntity")
@Table(name = "check-payment_tbl")

public class CheckPayment extends Payment{
    @Id
    @SequenceGenerator(name = "checkPaymentSeq", sequenceName = "check_payment_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checkPaymentSeq")
    @Column(name = "checkPayment_id",length = 20)
    private Long id;

    @Pattern(regexp = "^{6}$",message = "Invalid Check Number")
    @Column(name = "checkPayment_checkNumber",length = 6,unique = true)
    private String checkNumber;// شماره چک

    @Column(name ="checkPayment_checkDueDate")
    @PastOrPresent(message = "Invalid Date")
    private LocalDateTime checkDueDate;//تاریخ سررسید چک

    @ManyToOne(cascade = CascadeType.PERSIST)
    private CashDesk cashDesk;// صندوق

    @OneToOne(cascade = CascadeType.PERSIST)
    private  FinancialTransaction financialTransaction;

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
