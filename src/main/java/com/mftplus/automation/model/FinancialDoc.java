package com.mftplus.automation.model;

import com.github.mfathi91.time.PersianDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
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

@Entity(name = "financialDocEntity")
@Table(name = "financial_doc_tbl")
public class FinancialDoc extends Base{
    @Id
    @SequenceGenerator(name = "financialDocSeq", sequenceName = "financial_doc_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialDocSeq")
    @Column(name = "financialDoc_id",length = 20)
    private Long id;

    @Pattern(regexp = "^{1,5}$",message = "Invalid Doc Number")
    @Column(name ="financialDoc_docNumber" ,length =5, unique = true)
    private Long docNumber;//شماره سند

    @Column(name ="financialDoc_dateTime")
    @PastOrPresent(message = "تاریخ معتبر نیست")
    private LocalDateTime dateTime;//تاریخ


    @Pattern(regexp = "^{1,15}$",message = "Invalid Amount")
    @Column(name ="financialTransaction_amount" ,length =15)
    private Long amount; // مقدار پول معامله شده

    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Description")
    @Column(name ="financialTransaction_description" ,length =20 )
    private String description; //بابت


    @Transient
    private LocalDateTime faDateTime;

    public String getFaDateTime() {
        return PersianDateTime.fromGregorian(dateTime).toString();
    }

    public void setFaDateTime(String faDateTime) {
        this.dateTime = PersianDateTime.parse(faDateTime).toGregorian();
    }
}