package com.mftplus.automation.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

@Entity(name = "userRolesEntity")
@Table(name = "user_roles")

public class Roles extends Base implements Serializable {
    @EmbeddedId
    private CompositeKey compositeKey;
}
