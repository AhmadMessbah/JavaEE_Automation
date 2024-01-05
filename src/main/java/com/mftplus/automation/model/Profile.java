package com.mftplus.automation.model;

import com.github.mfathi91.time.PersianDate;
import jakarta.ejb.Local;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "profileEntity")
@Table(name = "profile_tbl")
public class Profile {
    @Id
    @SequenceGenerator(name = "profileSeq", sequenceName = "profile_seq")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "profileSeq")
    private Long id;

    private String nationalCode;
    private LocalDate birthDate;

    @Transient
    private LocalDate faBirthDate;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Attach attach;

    public String getFaBirthDate() {
        return PersianDate.fromGregorian(birthDate).toString();
    }

    public void setFaBirthDate(String faBirthDate) {
        this.birthDate = PersianDate.parse(faBirthDate).toGregorian();
    }
}


