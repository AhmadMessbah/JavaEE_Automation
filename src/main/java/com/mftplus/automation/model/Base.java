package com.mftplus.automation.model;

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
}
