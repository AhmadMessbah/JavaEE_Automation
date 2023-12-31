package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Organisation;
import com.mftplus.automation.service.OrganisationService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Transient;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@SessionScoped
public class OrganisationServiceImpl implements OrganisationService, Serializable {

    @PersistenceContext(unitName = "jk")
    private EntityManager entityManager;

    @Transient
    @Override
    public void save(Organisation organisation) throws Exception {
        entityManager.persist(organisation);


    }
    @Override
    public void edit(Organisation organisation) throws Exception {

    }

    @Override
    public void remove(Organisation organisation) throws Exception {

    }

    @Override
    public void removeById(Long id) throws Exception {

    }

    @Override
    public List<Organisation> findAll() throws Exception {
        return null;
    }

    @Override
    public Optional<Organisation> findById(Long id) throws Exception {
        return Optional.empty();
    }
}
