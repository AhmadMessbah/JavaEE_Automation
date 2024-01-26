package com.mftplus.automation.model;

import com.github.mfathi91.time.PersianDateTime;
import com.mftplus.automation.model.enums.FinancialTransactionType;
import com.mftplus.automation.model.enums.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
public class FinancialTransaction extends Base{
    @Id
    @SequenceGenerator(name = "financialTransactionSeq", sequenceName = "financial_transaction_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialTransactionSeq")
    @Column(name = "financialTransaction_id",length = 20)
    private Long id;

    @Column(name ="financialTransaction_dateTime")
    private LocalDateTime dateTime; //تاریخ

    @OneToOne(cascade = CascadeType.PERSIST)
    private User user; // پرداخت کننده

    @OneToOne(cascade = CascadeType.PERSIST)
    private Section referringSection; // واحد ارجاع کننده

    @OneToOne(cascade = CascadeType.MERGE)
    private FinancialDoc financialDoc;

    @Enumerated(EnumType.ORDINAL)
    private PaymentType paymentType;

//    private Payment payment;

    @Pattern(regexp = "^{1,20}$",message = "Invalid Tracking Code")
    @Column(name = "financialTransaction_trackingCode",length = 20,unique = true)
    private int trackingCode; // کد تراکنش

    private FinancialTransactionType transactionType;

    @Transient
    private LocalDateTime faDateTime;

    public String getFaDateTime() {
        return PersianDateTime.fromGregorian(dateTime).toString();
    }

    public void setFaDateTime(String faDateTime) {
        this.dateTime = PersianDateTime.parse(faDateTime).toGregorian();
    }
}