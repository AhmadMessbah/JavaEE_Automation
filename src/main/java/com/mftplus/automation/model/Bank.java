package com.mftplus.automation.model;

import com.google.gson.Gson;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "bankEntity")
@Table(name = "bank_tbl")
@RequestScoped
public class  Bank extends Base{
    @Id
    @SequenceGenerator(name = "bankSeq", sequenceName = "bank_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankSeq")
    @Column(name = "bank_id",length = 20)
    private Long id;

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Name")
    @Column(name ="bank_name" ,length =20 )
    private String name;// نام بانک

//    @Pattern(regexp = "^{16}$",message = "Invalid Account Number")
    @Column(name ="bank_accountNumber",length = 16, unique = true)
    private String accountNumber;// شماره حساب

//    @Pattern(regexp = "^{1,5}$",message = "Invalid Branch Code")
    @Column(name ="bank_branchCode" ,length =5)
    private int branchCode;// کد شعبه

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Branch Name")
    @Column(name ="bank_branchName" ,length =20)
    private String branchName;// نام شعبه

//    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Account Type")
    @Column(name ="bank_accountType" ,length =20)
    private String accountType;// نوع حساب بانکی

//    @Pattern(regexp = "^{1,15}$",message = "Invalid Account Balance")
    @Column(name ="bank_accountBalance" ,length =15)
    private Long accountBalance;// موجودی حساب


    @Override
    public String toString() {
        Gson gson=new Gson();
        return gson.toJson(this);
    }

}


