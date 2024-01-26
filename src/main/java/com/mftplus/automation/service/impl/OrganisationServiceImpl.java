package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Organisation;
import com.mftplus.automation.service.OrganisationService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@SessionScoped
public class OrganisationServiceImpl implements OrganisationService, Serializable {

    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Organisation organisation) throws Exception {
        entityManager.persist(organisation);


    }
    @Override
    @Transactional
    public void edit(Organisation organisation) throws Exception {
        entityManager.merge(organisation);

    }

    @Override
    @Transactional
    public void remove(Organisation organisation) throws Exception {
         organisation = entityManager.find(Organisation.class, organisation.getId());
        organisation.setDeleted(true);
        entityManager.merge(organisation);

    }

    @Override
    public void removeById(Long id) throws Exception {
        Organisation organisation = entityManager.find(Organisation.class, id);
        organisation.setDeleted(true);
        entityManager.merge(organisation);


    }

    @Override
    public List<Organisation> findAll() throws Exception {
        TypedQuery<Organisation> query = entityManager.createQuery("select oo from organisationEntity oo", Organisation.class);
        return query.getResultList();
    }

    @Override
    public Optional<Organisation> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(Organisation.class, id));
    }
    public Optional<Organisation> findByName(String name)throws Exception{
        TypedQuery<Organisation> query = (TypedQuery<Organisation>) entityManager.createQuery("select s from organisationEntity  s where s.name = :name");
        query.setParameter("name", name);
        List<Organisation> result = query.getResultList();
        return Optional.ofNullable((result.isEmpty()) ? null : result.get(0));
    }



}
