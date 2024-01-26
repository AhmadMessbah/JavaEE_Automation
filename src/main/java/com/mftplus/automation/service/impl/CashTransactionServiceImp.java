package com.mftplus.automation.service.impl;

import com.mftplus.automation.service.CashTransactionService;
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
public class CashTransactionServiceImp implements CashTransactionService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(CashTransaction cashTransaction) throws Exception {
        entityManager.persist(cashTransaction);
    }

    @Transactional
    @Override
    public void edit(CashTransaction cashTransaction) throws Exception {
        entityManager.merge(cashTransaction);
    }

    @Transactional
    @Override
    public void remove(CashTransaction cashTransaction) throws Exception {
        cashTransaction.setDeleted(true);
        entityManager.merge(cashTransaction);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        CashTransaction cashTransaction = entityManager.find(CashTransaction.class, id);
        cashTransaction.setDeleted(true);
        entityManager.merge(cashTransaction);
    }

    @Transactional
    @Override
    public List<CashTransaction> findAll() throws Exception {
        TypedQuery<CashTransaction> query = entityManager.createQuery("SELECT oo FROM cashTransactionEntity oo WHERE oo.deleted=false ", CashTransaction.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<CashTransaction> findByCashDeskNumber(int cashDeskNumber) throws Exception {
        TypedQuery<CashTransaction> query = entityManager.createQuery("SELECT oo FROM cashTransactionEntity oo WHERE oo.cashDesk.cashDeskNumber=:cashDeskNumber AND oo.deleted=false ", CashTransaction.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<CashTransaction> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(CashTransaction.class, id));
    }
}
