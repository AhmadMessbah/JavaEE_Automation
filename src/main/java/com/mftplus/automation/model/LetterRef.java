package com.mftplus.automation.model;

import com.github.mfathi91.time.PersianDate;
import com.mftplus.automation.model.enums.RefType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@Entity(name = "refReceiversEntity")
@Table(name = "ref_receivers_tbl")
public class RefReceivers implements Serializable {
    @Id
    @SequenceGenerator(name = "refReceiversSeq", sequenceName = "ref_receivers_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refReceiversSeq")
    @Column (name = "r_Id")
    private long id;

    @ManyToOne
    private Letter letter;

    @ManyToOne
    private  User refReceiver;
}
