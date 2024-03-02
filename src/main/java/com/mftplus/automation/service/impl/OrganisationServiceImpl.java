package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Organisation;
import com.mftplus.automation.service.OrganisationService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Slf4j
@SessionScoped
public class OrganisationServiceImpl implements OrganisationService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Organisation organisation) throws Exception {
        log.info("Organization Saved");
        entityManager.persist(organisation);
    }

    @Transactional
    @Override
    public void edit(Organisation organisation) throws Exception {
        entityManager.merge(organisation);
    }

    @Transactional
    @Override
    public void remove(Organisation organisation) throws Exception {
         organisation = entityManager.find(Organisation.class, organisation.getId());
        organisation.setDeleted(true);
        entityManager.merge(organisation);

    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        Organisation organisation = entityManager.find(Organisation.class, id);
        organisation.setDeleted(true);
        entityManager.merge(organisation);
    }

    @Transactional
    @Override
    public List<Organisation> findAll() throws Exception {
        TypedQuery<Organisation> query = entityManager.createQuery("select oo from organisationEntity oo where oo.deleted=false", Organisation.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<Organisation> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(Organisation.class, id));
    }

    @Transactional
    @Override
    public Optional<Organisation> findByName(String name) throws Exception {
        TypedQuery<Organisation> query = entityManager.createQuery("select oo from organisationEntity oo where oo.name=:name", Organisation.class);
        query.setParameter(name,"name");
        List<Organisation> result = query.getResultList();
        return Optional.ofNullable((result.isEmpty()) ? null : result.get(0));
    }
}
