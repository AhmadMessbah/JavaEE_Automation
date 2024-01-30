package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.FinancialTransaction;
import com.mftplus.automation.service.FinancialTransactionService;
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
public class FinancialTransactionServiceImpl implements FinancialTransactionService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(FinancialTransaction financialTransaction) throws Exception {
        entityManager.persist(financialTransaction);
    }

    @Transactional
    @Override
    public void edit(FinancialTransaction financialTransaction) throws Exception {
        entityManager.merge(financialTransaction);
    }

    @Transactional
    @Override
    public void remove(FinancialTransaction financialTransaction) throws Exception {
        financialTransaction.setDeleted(true);
        entityManager.merge(financialTransaction);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        FinancialTransaction financialTransaction = entityManager.find(FinancialTransaction.class, id);
        financialTransaction.setDeleted(true);
        entityManager.merge(financialTransaction);
    }

    @Transactional
    @Override
    public void removeByTrackingCode(int trackingCode) throws Exception{
        FinancialTransaction financialTransaction = entityManager.find(FinancialTransaction.class, trackingCode);
        financialTransaction.setDeleted(true);
        entityManager.merge(financialTransaction);
    }

    @Transactional
    @Override
    public List<FinancialTransaction> findAll() throws Exception {
        TypedQuery<FinancialTransaction> query = entityManager.createQuery("select oo from financialTransactionEntity oo WHERE oo.deleted=false ", FinancialTransaction.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<FinancialTransaction> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(FinancialTransaction.class, id));
    }

    @Transactional
    @Override
    public Optional<FinancialTransaction> findByTrackingCode(int trackingCode) throws Exception {
        return Optional.ofNullable(entityManager.find(FinancialTransaction.class, trackingCode));
    }

    @Transactional
    @Override
    public List<FinancialTransaction> findByDateTime(LocalDateTime dateTime) throws Exception {
        TypedQuery<FinancialTransaction> query=entityManager.createQuery("SELECT oo FROM financialTransactionEntity oo WHERE oo.dateTime=:dateTime AND oo.deleted=false",FinancialTransaction.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<FinancialTransaction> findByUser(String username) throws Exception {
        TypedQuery<FinancialTransaction> query=entityManager.createQuery("SELECT oo FROM financialTransactionEntity oo WHERE oo.user.username=:username AND oo.deleted=false",FinancialTransaction.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<FinancialTransaction> findBySection(String title) throws Exception {
        TypedQuery<FinancialTransaction> query=entityManager.createQuery("SELECT oo FROM financialTransactionEntity oo WHERE oo.referringSection.title=:title AND oo.deleted=false",FinancialTransaction.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<FinancialTransaction> findByFinancialDoc(Long docNumber) throws Exception {
        return Optional.ofNullable(entityManager.find(FinancialTransaction.class,docNumber));
    }
}