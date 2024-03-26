package com.mftplus.automation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
@SuperBuilder
public class CompositeKey implements Serializable {
    @Column(name = "role_name")
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "u_username")
    private User user;
}
