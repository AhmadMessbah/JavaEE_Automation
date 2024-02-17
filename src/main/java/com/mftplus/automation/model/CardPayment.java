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
@RequestScoped
@Entity(name = "cardPaymentEntity")
@Table(name = "card_payment_tbl")
public class CardPayment extends Payment{
    @Id
    @SequenceGenerator(name = "cardPaymentSeq", sequenceName = "card_payment_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardPaymentSeq")
    @Column(name = "cardPayment_id",length = 20)
    private Long id;

//    @Pattern(regexp = "^{1,20}$",message = "Invalid Deposit Code")
    @Column(name = "cardPayment_depositCode",length = 20)
    private String depositCode; // کد واریز

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Bank bankInvolved; //  حساب بانک درگیر

    @Override
    public String toString() {
        Gson gson=new Gson();
        return gson.toJson(this);
    }
}
