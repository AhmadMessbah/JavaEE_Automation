package com.mftplus.automation.service;

import com.mftplus.automation.model.Organisation;

import java.util.List;
import java.util.Optional;

public interface OrganisationService {
    void save(Organisation organisation) throws Exception;
    void edit(Organisation organisation) throws Exception;
    void remove(Organisation organisation) throws Exception;
    void removeById(Long id) throws Exception;

    List<Organisation> findAll() throws Exception;
    Optional<Organisation> findById(Long id) throws Exception;
    Optional<Organisation> findByName(String name) throws Exception;
}
