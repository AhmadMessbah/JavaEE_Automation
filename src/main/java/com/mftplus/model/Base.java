package com.mftplus.model;

import com.google.gson.Gson;
import jakarta.json.bind.annotation.JsonbTransient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.MappedSuperclass;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@MappedSuperclass
public class Base {
    //todo : version id creates new row for editing
//    @Version
//    @JsonbTransient
//    private Long versionId;

    @JsonbTransient
    private Boolean deleted;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}