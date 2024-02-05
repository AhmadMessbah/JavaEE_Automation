package com.mftplus.automation.model;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "StuffStorageEntity")
@Table(name = "stuffStorage_tbl")
@RequestScoped

public class StuffStorage {
    @Id
    @SequenceGenerator(name = "stuffStorageSeq", sequenceName = "stuffStorage_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ss_id", nullable = false)
    private int id;


    @Column(name = "ss_name", length = 20, nullable = false)
    private String name;

    @Column(name = "ss_count", length = 20, nullable = false)
    private int count;
    

}

