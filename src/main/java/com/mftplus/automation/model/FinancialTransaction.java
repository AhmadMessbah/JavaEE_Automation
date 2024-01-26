package com.mftplus.automation.model;

import com.github.mfathi91.time.PersianDateTime;
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

    @Pattern(regexp = "^{1,20}$",message = "Invalid Tracking Code")
    @Column(name = "financialTransaction_trackingCode",length = 20,unique = true)
    private int trackingCode; // کد تراکنش

    @Pattern(regexp = "^{1,15}$",message = "Invalid Amount")
    @Column(name ="financialTransaction_amount" ,length =15)
    private Long amount; // مقدار پول معامله شده

    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Description")
    @Column(name ="financialTransaction_description" ,length =20 )
    private String description; //بابت

    @Column(name ="financialTransaction_dateTime")
    private LocalDateTime dateTime; //تاریخ

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User payer; // پرداخت کننده

    @OneToOne(cascade = CascadeType.PERSIST)
    private Section referringSection; // واحد ارجاع کننده

    @ManyToOne(cascade = CascadeType.MERGE)
    private FinancialDoc financialDoc;

    @Transient
    private LocalDateTime faDateTime;

    public String getFaDateTime() {
        return PersianDateTime.fromGregorian(dateTime).toString();
    }

    public void setFaDateTime(String faDateTime) {
        this.dateTime = PersianDateTime.parse(faDateTime).toGregorian();
    }
}