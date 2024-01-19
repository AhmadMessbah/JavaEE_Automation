package com.mftplus.automation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

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
    @SequenceGenerator(name = "transferMethodSeq", sequenceName = "transfer_method_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transferMethodSeq")
    @Column (name = "t_Id")
    private long id;

    @Column(name = "t_post" , length = 20)
    private String post;

    @Column(name = "t_deliverLetter" , length = 20)
    private String deliverLetter;

    @Column(name = "t_fax" , length = 20)
    private String fax;

    @Column(name = "t_in_system" , length = 20)
    private String inSystem;

    @Email
    @Column(name = "t_email" , length = 30)
    private String email;

    //todo email address and post code







}
