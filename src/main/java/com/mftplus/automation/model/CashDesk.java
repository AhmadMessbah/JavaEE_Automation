package com.mftplus.automation.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@RequestScoped
@Entity(name = "cashDeskEntity")
@Table(name = "cash-desk_tbl")
public class CashDesk extends Base{
    @Id
    @SequenceGenerator(name = "cashDeskSeq", sequenceName = "cash-desk_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cashDeskSeq")
    @Column(name = "cashDesk_id",length = 20)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z\\s]{3,20}$",message = "Invalid Name")
    @Column(name ="cashDesk_name" ,length =20 )
    private String name;// نام صندوق

    @Pattern(regexp = "^{1,2}$",message = "Invalid Cash Desk Number")
    @Column(name ="cashDesk_cashDeskNumber",length = 2,unique = true)
    private int cashDeskNumber;// شماره صندوق

    @Pattern(regexp = "^{1,15}$",message = "Invalid Cash Balance")
    @Column(name ="cashDesk_cashBalance" ,length =15)
    private Long cashBalance;//  موجودی صندوق

    @OneToOne(cascade = CascadeType.PERSIST)
    private User cashier;//صندوقدار
}