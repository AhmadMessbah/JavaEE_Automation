package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.*;
import com.mftplus.automation.model.enums.TransactionType;
import com.mftplus.automation.service.FinancialTransactionService;
import com.mftplus.automation.service.impl.FinancialTransactionService;
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
        entityManager.remove(financialTransaction);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        FinancialTransaction financialTransaction = entityManager.find(FinancialTransaction.class, id);
        entityManager.remove(financialTransaction);
    }

    @Transactional
    @Override
    public void removeByTrackingCode(int trackingCode) throws Exception {
        FinancialTransaction financialTransaction = entityManager.find(FinancialTransaction.class, trackingCode);
        entityManager.remove(financialTransaction);
    }

    @Transactional
    @Override
    public List<FinancialTransaction> findAll() throws Exception {
        TypedQuery<FinancialTransaction> query = entityManager.createQuery("select p from financialTransactionEntity p", FinancialTransaction.class);
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
    public List<FinancialTransaction> findByBankInvolved(Long id) throws Exception {
        Bank bank=entityManager.find(Bank.class,id);
        TypedQuery<FinancialTransaction> query=entityManager.createQuery("SELECT oo FROM financialTransactionEntity oo WHERE oo.bankInvolved=:bank",FinancialTransaction.class);
        query.setParameter("bank",bank);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<FinancialTransaction> findByDateTime(LocalDateTime dateTime) throws Exception {
        TypedQuery<FinancialTransaction> query=entityManager.createQuery("SELECT oo FROM financialTransactionEntity oo WHERE oo.dateTime=:dateTime",FinancialTransaction.class);
        query.setParameter("dateTime",dateTime);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<FinancialTransaction> findByType(TransactionType transactionType) throws Exception {
        TypedQuery<FinancialTransaction> query=entityManager.createQuery("SELECT oo FROM financialTransactionEntity oo WHERE oo.transactionType=:type",FinancialTransaction.class);
        query.setParameter("type",transactionType);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<FinancialTransaction> findByUserId(Long id) throws Exception {
        User user=entityManager.find(User.class,id);
        TypedQuery<FinancialTransaction> query=entityManager.createQuery("SELECT oo FROM financialTransactionEntity oo WHERE oo.user=:user",FinancialTransaction.class);
        query.setParameter("user",id);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<FinancialTransaction> findByFinancialDoc(Long id) throws Exception {
        FinancialDoc financialDoc=entityManager.find(FinancialDoc.class,id);
        return Optional.ofNullable(entityManager.find(FinancialTransaction.class,financialDoc));
    }
}