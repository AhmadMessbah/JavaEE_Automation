package com.mftplus.automation.model;

import com.github.mfathi91.time.PersianDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

@MappedSuperclass
public class Payment extends Base {
    @Pattern(regexp = "^{1,15}$", message = "Invalid Amount")
    @Column(name = "financialTransaction_amount", length = 15)
    private Long amount;

    @Column(name = "financialDoc_dateTime")
    @PastOrPresent(message = "تاریخ معتبر نیست")
    private LocalDateTime dateTime;

    @Transient
    private LocalDateTime faDateTime;

    public String getFaDateTime() {
        return PersianDateTime.fromGregorian(dateTime).toString();
    }

    public void setFaDateTime(String faDateTime) {
        this.dateTime = PersianDateTime.parse(faDateTime).toGregorian();
    }
}
