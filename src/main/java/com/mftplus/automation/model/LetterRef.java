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

@Entity(name = "letterRefEntity")
@Table(name = "letter_ref_tbl")

@NamedQueries({
        @NamedQuery(name = "LetterRef.FindByLetter",query = "select oo from letterRefEntity  oo where oo.letterId=:letterId")
})
public class LetterRef implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "r_Id")
    private long id;

    @ManyToOne
    private Letter letterId;

    @Enumerated (EnumType.ORDINAL)
    private RefType refType;

    @ManyToOne
    private User refSender;

    @ManyToOne
    private User refReceiver;

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
}
