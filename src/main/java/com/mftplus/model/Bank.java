package com.mftplus.model;

import com.mftplus.model.enums.AccountType;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "bankEntity")
@Table(name = "bank_tbl")
@RequestScoped
public class Bank extends Base implements Serializable {

    @Id
    @SequenceGenerator(name = "bankSeq", sequenceName = "bank_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankSeq")
    @Column(name = "bank_id", length = 20)
    private Long id;

    @Column(name = "bank_name",columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Name")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String name;// نام بانک

    @Column(name = "bank_accountNumber", length = 16)
    @Pattern(regexp = "^[0-9]{5,16}$", message = "Invalid Account Number")
    @Size(min = 5, max = 16, message = " Account Number must be between 5 and 16 characters")
    @NotBlank(message = "Should Not Be Null")
    private String accountNumber;// شماره حساب

    @Column(name = "bank_branchCode", length = 3)
    @Min(value = 1, message = "Branch code must be at least 1")
    @Max(value = 999, message = "Branch code must be at most 999")
    private Long branchCode;// کد شعبه

    @Column(name = "bank_branchName",columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Branch Name")
    @Size(min = 3, max = 20, message = "Branch Name must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String branchName;// نام شعبه

    @Enumerated(EnumType.ORDINAL)
    private AccountType accountType;// نوع حساب بانکی

    @Column(name = "bank_accountBalance", length = 10)
    @Positive(message = "The account balance must be a positive number.")
    @Min(value = 1, message = "The account balance must be at least 1.")
    @Max(value = 1999999999, message = "The account balance cannot exceed 1999999999.")
    private Long accountBalance;// موجودی حساب
}