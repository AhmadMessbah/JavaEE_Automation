package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.FinancialDoc;
import com.mftplus.automation.service.FinancialDocService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@SessionScoped
public class FinancialDocServiceImpl implements FinancialDocService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager  entityManager;

    @Transactional
    @Override
    public void save(FinancialDoc financialDoc) throws Exception {
        entityManager.persist(financialDoc);
    }

    @Transactional
    @Override
    public void edit(FinancialDoc financialDoc) throws Exception {
        entityManager.merge(financialDoc);
    }

    @Transactional
    @Override
    public void remove(FinancialDoc financialDoc) throws Exception {
        financialDoc.setDeleted(true);
        entityManager.merge(financialDoc);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        FinancialDoc financialDoc = entityManager.find(FinancialDoc.class, id);
        financialDoc.setDeleted(true);
        entityManager.merge(financialDoc);
    }

    @Override
    public void removeByDocNumber(Long docNumber) throws Exception {
        FinancialDoc financialDoc = entityManager.find(FinancialDoc.class, docNumber);
        financialDoc.setDeleted(true);
        entityManager.merge(financialDoc);
    }

    @Transactional
    @Override
    public Optional<FinancialDoc> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(FinancialDoc.class,id));
    }

    @Override
    public Optional<FinancialDoc> findByDocNumber(Long docNumber) throws Exception {
        return Optional.ofNullable(entityManager.find(FinancialDoc.class,docNumber));
    }

    @Transactional
    @Override
    public List<FinancialDoc> findByDescription(String description) throws Exception {
        TypedQuery<FinancialDoc> query=entityManager.createQuery("SELECT oo FROM financialDocEntity oo WHERE oo.description=:description AND oo.deleted=false",FinancialDoc.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<FinancialDoc> findAll() throws Exception {
        TypedQuery<FinancialDoc> query= entityManager.createQuery("SELECT oo FROM financialDocEntity oo WHERE oo.deleted=false",FinancialDoc.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<FinancialDoc> findByDateTime(LocalDateTime dateTime) throws Exception {
        TypedQuery<FinancialDoc> query=entityManager.createQuery("SELECT oo FROM financialDocEntity oo WHERE oo.dateTime=:dateTime AND oo.deleted=false",FinancialDoc.class);
        return query.getResultList();
    }
}
