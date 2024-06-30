package com.mftplus.model;

import com.github.mfathi91.time.PersianDate;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "financialDocEntity")
@Table(name = "financial_doc_tbl")
@RequestScoped
public class FinancialDoc extends Base{

    @Id
    @SequenceGenerator(name = "financialDocSeq", sequenceName = "financial_doc_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financialDocSeq")
    @Column(name = "fDoc_id",length = 20)
    private Long id;

    @Column(name ="fDoc_docNumber" ,length =4, unique = true)
    @Positive(message = "The doc number must be a positive number.")
    @Min(value = 1, message = "The doc number must be at least 1.")
    @Max(value = 9999, message = "The doc number cannot exceed 9999.")
    private Long docNumber;//شماره سند

    @Column(name ="fDoc_dateTime")
    @PastOrPresent(message = "Invalid Date")
    private LocalDate date;//تاریخ

    @Column(name ="fDoc_description" ,length =999 )
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,999}$", message = "Invalid Description")
    @Size(min = 3, max = 999, message = " description must be between 3 and 999 characters")
    @NotBlank(message = "Should Not Be Null")
    private String description; //بابت

    @OneToOne(fetch = FetchType.EAGER)
    private FinancialTransaction financialTransaction;

    @Transient
    private String faDate;

    public String getFaDate() {
        return String.valueOf(PersianDate.fromGregorian(date));
    }

    public void setFaDate(String faDate) {
        this.date =PersianDate.parse(faDate).toGregorian();
    }
}