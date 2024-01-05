package com.mftplus.automation.model;

import com.github.mfathi91.time.PersianDate;
import com.mftplus.automation.model.enums.Classification;
import com.mftplus.automation.model.enums.TransferMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@Entity (name = "letterEntity")
@Table (name = "letter_tbl")

@NamedQueries({
        @NamedQuery(name = "Letter.FindByTitle",query = "select oo from letterEntity oo where oo.title=:title"),
        @NamedQuery(name = "Letter.FindByContext",query = "select oo from letterEntity oo where oo.context=:context"),
        @NamedQuery(name = "Letter.FindByDate",query = "select oo from letterEntity oo where oo.date=:date")
})
public class Letter implements Serializable {
    @Id
    @SequenceGenerator(name = "letterSeq", sequenceName = "letter_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "letterSeq")
    @Column (name = "l_Id")
    private long id;

    @Column (name = "l_title")
    private String title;

    @OneToOne
    private GeneratedSequence registerNumber;

    @Column (name = "l_register_date_and_time")
    private LocalDateTime registerDateAndTime;

    @Transient
    private LocalDateTime faRegisterDateAndTime;

    public String getFaRegisterDateAndTime() {
        return PersianDate.fromGregorian(LocalDate.from(registerDateAndTime)).toString();
    }

    public void setFaRegisterDateAndTime(String faRegisterDateAndTime) {
        this.registerDateAndTime = LocalDateTime.from(PersianDate.parse(faRegisterDateAndTime).toGregorian());
    }

    @Column (name = "l_letter_number" , length = 30 , unique = true)
    private String letterNumber;

    public void letterNumber(){
        String faRegisterDateAndTime = getFaRegisterDateAndTime();
        String registerNumber = String.valueOf(getRegisterNumber());

        setLetterNumber(faRegisterDateAndTime + registerNumber);
    }

    //needs to be in persian
    @Column (name = "l_date")
    private LocalDate date;

    @Transient
    private LocalDate faDate;

    public String getFaDate() {
        return PersianDate.fromGregorian(date).toString();
    }

    public void setFaDate(String faDate) {
        this.date = PersianDate.parse(faDate).toGregorian();
    }

    @Column (name = "l_subject", length = 25)
    private String subject;

    @Column (name = "l_context")
    //for search
    private String context;

    //what is indicator code?
    @Column (name = "l_indicator_code", length = 20)
    private String indicatorCode;

    @Column (name = "l_receiver_name" , length = 25)
    private String receiverName;

    @Column (name = "l_receiver_title" , length = 25)
    private String receiverTitle;

    @ManyToMany
    private List<User> receivers;

    @Column (name = "l_sender_name" , length = 25)
    private String senderName;

    @Column (name = "l_sender_title", length = 25)
    private String senderTitle;

    @Column (name = "l_image")
    private String image;

    @Enumerated (EnumType.ORDINAL)
    private Classification classification;

    //???
    @ManyToMany
    private List<User> carbonCopies;

    @ManyToOne
    private User user;

    @ManyToOne
    private TransferMethod transferMethod;

    //what is this?
    @Column (name = "l_natural_or_legal")
    private Boolean naturalOrLegal;











}
