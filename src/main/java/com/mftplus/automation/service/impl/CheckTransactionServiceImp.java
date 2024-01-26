package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.CheckTransaction;
import com.mftplus.automation.service.CheckTransactionService;
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
public class CheckTransactionServiceImp implements CheckTransactionService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(CheckTransaction checkTransaction) throws Exception {
        entityManager.persist(checkTransaction);
    }

    @Transactional
    @Override
    public void edit(CheckTransaction checkTransaction) throws Exception {
        entityManager.merge(checkTransaction);
    }

    @Transactional
    @Override
    public void remove(CheckTransaction checkTransaction) throws Exception {
        checkTransaction.setDeleted(true);
        entityManager.merge(checkTransaction);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        CheckTransaction checkTransaction=entityManager.find(CheckTransaction.class,id);
        checkTransaction.setDeleted(true);
        entityManager.merge(checkTransaction);
    }

    @Transactional
    @Override
    public void removeByCheckNumber(String checkNumber) throws Exception {
        CheckTransaction checkTransaction=entityManager.find(CheckTransaction.class,checkNumber);
        checkTransaction.setDeleted(true);
        entityManager.merge(checkTransaction);
    }

    @Transactional
    @Override
    public List<CheckTransaction> findAll() throws Exception {
        TypedQuery<CheckTransaction> query = entityManager.createQuery("SELECT oo FROM checkTransactionEntity oo WHERE oo.deleted=false ", CheckTransaction.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<CheckTransaction> findByCheckDueDate(LocalDateTime checkDueDate) throws Exception {
        TypedQuery<CheckTransaction> query = entityManager.createQuery("SELECT oo FROM checkTransactionEntity oo WHERE oo.checkDueDate=:checkDueDate AND oo.deleted=false ", CheckTransaction.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<CheckTransaction> findByCashDeskNumber(int cashDeskNumber) throws Exception {
        return Optional.ofNullable(entityManager.find(CheckTransaction.class, cashDeskNumber));
    }

    @Transactional
    @Override
    public Optional<CheckTransaction> findByCheckNumber(String checkNumber) throws Exception {
        return Optional.ofNullable(entityManager.find(CheckTransaction.class, checkNumber));
    }

    @Transactional
    @Override
    public Optional<CheckTransaction> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(CheckTransaction.class, id));
    }
}
