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

@Entity(name = "checkPaymentEntity")
@Table(name = "check-payment_tbl")
@RequestScoped
public class CheckPayment extends Base{

    @Id
    @SequenceGenerator(name = "checkPaymentSeq", sequenceName = "check_payment_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checkPaymentSeq")
    @Column(name = "checkPayment_id",length = 20)
    private Long id;

    @Column(name = "checkPayment_checkNumber",length = 16,unique = true)
    @Pattern(regexp = "^[0-9]{5,16}$", message = "Invalid Check Number")
    @Size(min = 5, max = 16, message = " Check Number must be between 5 and 16 characters")
    @NotBlank(message = "Should Not Be Null")
    private String checkNumber;// شماره چک

    @Column(name = "checkPayment_amount", length = 10)
    @Positive(message = "The amount must be a positive number.")
    @Min(value = 1, message = "The amount must be at least 1.")
    @Max(value = 1999999999, message = "The amount cannot exceed 1999999999.")
    private Long amount; // مقدار پول معامله شده

    @Column(name ="checkPayment_checkDueDate")
    @FutureOrPresent(message = "Invalid Date")
    private LocalDate checkDueDate;//تاریخ سررسید چک

    @Transient
    private String faCheckDueDate;

    public String getFaCheckDueDate() {
        return String.valueOf(PersianDate.fromGregorian(checkDueDate));
    }

    public void setFaCheckDueDate(String faCheckDueDate) {
        this.checkDueDate = PersianDate.parse(faCheckDueDate).toGregorian();
    }
}