package com.mftplus.automation.model;

import com.github.mfathi91.time.PersianDateTime;
import com.mftplus.automation.model.enums.ReferencePriority;
import com.mftplus.automation.model.enums.ReferenceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@Entity(name = "referenceEntity")
@Table(name = "reference_tbl")

public class Reference extends Base implements Serializable {
    @Id
    @SequenceGenerator(name = "referenceSeq", sequenceName = "reference_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "referenceSeq")
    @Column (name = "r_Id")
    private long id;

    @ManyToOne (fetch = FetchType.EAGER,cascade = {CascadeType.MERGE})
    private Letter letterId;

    @Enumerated (EnumType.ORDINAL)
    private ReferenceType refType;

    @Enumerated (EnumType.ORDINAL)
    private ReferencePriority priority;

    @ManyToOne
    @JoinColumn(name = "reference_sender",nullable = false)
    private User referenceSenderId;

    @ManyToOne
    @JoinColumn(name = "reference_receiver",nullable = false)
    private User referenceReceiverId;

    @Column (name = "r_date_and_time")
    private LocalDateTime refDateAndTime;

//    @Transient
//    private LocalDateTime faRefDateAndTime;
//
//    public String getFaRefDateAndTime() {
//        return PersianDate.fromGregorian(LocalDate.from(refDateAndTime)).toString();
//    }
//
//    public void setFaRefDateAndTime(String faRefDateAndTime) {
//        this.refDateAndTime = LocalDateTime.from(PersianDate.parse(faRefDateAndTime).toGregorian());
//    }

    @Column (name = "r_expiration")
    private LocalDateTime expiration;

    @Transient
    private String faExpiration;

    public String getFaExpiration() {
       return PersianDateTime.fromGregorian(expiration).toString();
    }

    public void setFaExpiration(String faExpiration) {
        this.expiration = PersianDateTime.parse(faExpiration, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toGregorian();
    }

    @Column (name = "r_paraph" , length = 50)
    private String paraph;

    @Column (name = "r_explanation" , length = 50)
    private String explanation;

    @Column(name = "r_status")
    private boolean status;

    @Column(name = "r_validate")
    private boolean validate;


}
