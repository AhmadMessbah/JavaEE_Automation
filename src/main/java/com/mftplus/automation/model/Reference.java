package com.mftplus.automation.model;


import com.github.mfathi91.time.PersianDate;
import com.mftplus.automation.model.enums.ReferencePriority;
import com.mftplus.automation.model.enums.ReferenceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "referenceEntity")
@Table(name = "reference_tbl")
public class Reference {

@Id
    @SequenceGenerator(name = "letterSeq", sequenceName = "letter_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "letterSeq")
    @Column (name = "r_Id")
    private long id;

    @ManyToOne (cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    private Letter letterId;

    @Enumerated (EnumType.ORDINAL)
    private ReferenceType refType;

    @ManyToOne (cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    private User referenceSenderId;

    @ManyToOne (cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    private User referenceReceiverId;

    @Column (name = "r_date_and_time")
    private LocalDateTime refDateAndTime;

    @Transient
    private LocalDateTime faRefDateAndTime;

    public String getFaRefDateAndTime() {
        return PersianDate.fromGregorian(LocalDate.from(refDateAndTime)).toString();
    }

    public void setFaRefDateAndTime(String faRefDateAndTime) {
        this.refDateAndTime = LocalDateTime.from(PersianDate.parse(faRefDateAndTime).toGregorian());
    }

    @Column (name = "r_expiration")
    private LocalDateTime expiration;

    @Transient
    private LocalDateTime faExpiration;

    public String getFaExpiration() {
        return PersianDate.fromGregorian(LocalDate.from(expiration)).toString();
    }

    public void setFaExpiration(String faExpiration) {
        this.expiration = LocalDateTime.from(PersianDate.parse(faExpiration).toGregorian());
    }

    @Column (name = "r_paraph" , length = 50)
    private String paraph;

    @Column (name = "r_comment" , length = 50)
    private String comment;

    @Column(name = "r_status")
    private boolean status;

    @Column(name = "r_validate")
    private boolean validate;

    @Enumerated (EnumType.ORDINAL)
    private ReferencePriority priority;
}



