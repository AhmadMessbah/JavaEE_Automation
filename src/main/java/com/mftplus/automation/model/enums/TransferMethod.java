package com.mftplus.automation.model.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

@Entity(name = "transferMethodEntity")
@Table(name = "transfer_method_tbl")
public class TransferMethod implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

}
