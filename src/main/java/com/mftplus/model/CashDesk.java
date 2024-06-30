package com.mftplus.model;

import jakarta.enterprise.context.RequestScoped;
import jakarta.json.bind.annotation.JsonbTransient;
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
@ToString(exclude = "cashier")

@Entity(name = "cashDeskEntity")
@Table(name = "cash_desk_tbl")
@RequestScoped
public class CashDesk extends Base implements Serializable {
    @Id
    @SequenceGenerator(name = "cashDeskSeq", sequenceName = "cash_desk_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cashDeskSeq")
    @Column(name = "cashDesk_id", length = 20)
    private Long id;

    @Column(name = "cashDesk_name", columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Name")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String name;

    @Column(name = "cashDesk_cashDeskNumber", length = 3)
    @Min(value = 1, message = "Cash Desk Number must be at least 1")
    @Max(value = 999, message = "Cash Desk Number must be at most 999")
    private Long cashDeskNumber;

    @Column(name = "cashDesk_cashBalance", length = 10)
    @Positive(message = "The cash balance must be a positive number.")
    @Min(value = 1, message = "The cash balance must be at least 1.")
    @Max(value = 1999999999, message = "The cash balance cannot exceed 1999999999.")
    private Long cashBalance;

    @JsonbTransient
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User cashier;
}
