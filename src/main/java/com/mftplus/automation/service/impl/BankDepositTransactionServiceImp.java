package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.BankDepositTransaction;
import com.mftplus.automation.service.BankDepositTransactionService;
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
public class BankDepositTransactionServiceImp implements BankDepositTransactionService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(BankDepositTransaction bankDepositTransaction) throws Exception {
        entityManager.persist(bankDepositTransaction);
    }

    @Transactional
    @Override
    public void edit(BankDepositTransaction bankDepositTransaction) throws Exception {
        entityManager.merge(bankDepositTransaction);
    }

    @Transactional
    @Override
    public void remove(BankDepositTransaction bankDepositTransaction) throws Exception {
        bankDepositTransaction.setDeleted(true);
        entityManager.merge(bankDepositTransaction);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        BankDepositTransaction bankDepositTransaction = entityManager.find(BankDepositTransaction.class, id);
        bankDepositTransaction.setDeleted(true);
        entityManager.merge(bankDepositTransaction);
    }

    @Transactional
    @Override
    public List<BankDepositTransaction> findAll() throws Exception {
        TypedQuery<BankDepositTransaction> query = entityManager.createQuery("SELECT oo FROM bankDepositTransactionEntity oo WHERE oo.deleted=false ", BankDepositTransaction.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<BankDepositTransaction> findByDepositCode(String depositCode) throws Exception {
        TypedQuery<BankDepositTransaction> query = entityManager.createQuery("SELECT oo FROM bankDepositTransactionEntity oo WHERE oo.depositCode=:depositCode AND oo.deleted=false ", BankDepositTransaction.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<BankDepositTransaction> findByBankInvolved(String accountNumber) throws Exception {
        TypedQuery<BankDepositTransaction> query = entityManager.createQuery("SELECT oo FROM bankDepositTransactionEntity oo WHERE oo.bankInvolved.accountNumber=:accountNumber AND oo.deleted=false ", BankDepositTransaction.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<BankDepositTransaction> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(BankDepositTransaction.class, id));
    }
}
