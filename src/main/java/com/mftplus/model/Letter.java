package com.mftplus.model;

import com.github.mfathi91.time.PersianDate;
import com.mftplus.model.enums.LetterAccessLevel;
import com.mftplus.model.enums.LetterType;
import com.mftplus.model.enums.TransferMethod;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@Entity (name = "letterEntity")
@Table (name = "letter_tbl")
@RequestScoped
public class Letter extends Base implements Serializable {

    @Id
    @SequenceGenerator(name = "letterSeq", sequenceName = "letter_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "letterSeq")
    @Column (name = "l_id")
    private long id;

    @Column (name = "l_title" ,columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid Title")
    @Size(min = 3, max = 20, message = "Title must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String title;

    //todo : what is the actual min and max for letter number?
    @Column (name = "l_letter_number" , columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[0-9]{1,20}$", message = "Invalid LetterNumber")
    @Size(min = 1, max = 20, message = " LetterNumber must be between 1 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String letterNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    //ref receivers
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> userList;

    public void addUser(User user){
        if (userList==null){
            userList=new ArrayList<>();
        }
        userList.add(user);
    }

    //todo : why validation fails for persian value?
    @Column (name = "l_sender_name" , columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid SenderName")
    @Size(min = 3, max = 20, message = "SenderName must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String senderName;

    @Column (name = "l_sender_title", columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid SenderTitle")
    @Size(min = 3, max = 20, message = "SenderTitle must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String senderTitle;

    @Column (name = "l_receiver_name" , columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid ReceiverName")
    @Size(min = 3, max = 20, message = "ReceiverName must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String receiverName;

    @Column (name = "l_receiver_title" , columnDefinition = "NVARCHAR2(20)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,20}$", message = "Invalid ReceiverTitle")
    @Size(min = 3, max = 20, message = "ReceiverTitle must be between 3 and 20 characters")
    @NotBlank(message = "Should Not Be Null")
    private String receiverTitle;

    //todo : attachment needed
    @Column (name = "l_file")
    private String image;

    @Enumerated (EnumType.ORDINAL)
    private LetterAccessLevel accessLevel;

    @Enumerated (EnumType.ORDINAL)
    private TransferMethod transferMethod;

    @Enumerated (EnumType.ORDINAL)
    private LetterType letterType;

    @Column (name = "l_date")
    @FutureOrPresent(message = "Invalid letter date")
    @NotNull(message = "Should Not Be Null")
    private LocalDate date;

    @Transient
    private String faDate;

    public String getFaDate() {
        return String.valueOf(PersianDate.fromGregorian(date));
    }

    public void setFaDate(String faDate) {
        this.date =PersianDate.parse(faDate).toGregorian();
    }

    //todo : max size for context?
    @Column (name = "l_context")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,250}$", message = "Invalid Context")
    @Size(min = 3,max = 250,message = "Context must be at least 3 characters")
    private String context;

    //todo : does this need @futureOrPresent? I set it as localDateTime.now in servlet
    @Column(name = "register_date_and_time")
    private LocalDateTime registerDateAndTime;
}