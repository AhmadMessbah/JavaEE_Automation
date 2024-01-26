package com.mftplus.automation.model;

<<<<<<< HEAD
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@MappedSuperclass
public class Base {
    @Version
    private  Long versionId;

    private boolean deleted;
=======
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@MappedSuperclass
public class Base {
>>>>>>> origin/projects
}
