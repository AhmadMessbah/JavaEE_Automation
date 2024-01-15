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

public class Letter implements Serializable {
    @Id
    @SequenceGenerator(name = "letterSeq", sequenceName = "letter_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "letterSeq")
    @Column (name = "l_Id")
    private long id;

    @Column (name = "l_title" , length = 20)
    private String title;

    @Column (name = "l_letter_number" , length = 30 , unique = true)
    private String letterNumber;

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
    @Field(termVector = TermVector.YES)
    private String context;

    @Column (name = "l_receiver_name" , length = 25)
    private String receiverName;

    @Column (name = "l_receiver_title" , length = 25)
    private String receiverTitle;

    @Column (name = "l_sender_name" , length = 25)
    private String senderName;

    @Column (name = "l_sender_title", length = 25)
    private String senderTitle;

    @Column (name = "l_image")
    private String image;

    @Enumerated (EnumType.ORDINAL)
    private LetterAccessLevel accessLevel;

    //todo rethink
    @ManyToMany (cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    private List<User> carbonCopies;

    @ManyToOne (cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    private User user;

    @ManyToOne (cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    private TransferMethod transferMethod;

    @Enumerated (EnumType.ORDINAL)
    private LetterType letterType;

    @OneToOne
    private LetterRegister registerNumber;

    @ManyToOne
    private Secretariat indicatorCode;

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

}
