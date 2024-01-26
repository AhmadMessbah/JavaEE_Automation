package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Bank;
import com.mftplus.automation.service.BankService;
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
public class BankServiceImpl implements BankService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Bank bank) throws Exception {
        entityManager.persist(bank);
    }

    @Transactional
    @Override
    public void edit(Bank bank) throws Exception {
        entityManager.merge(bank);
    }

    @Transactional
    @Override
    public void remove(Bank bank) throws Exception {
        bank.setDeleted(true);
        entityManager.merge(bank);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        Bank bank = entityManager.find(Bank.class, id);
        bank.setDeleted(true);
        entityManager.merge(bank);
    }

    @Transactional
    @Override
    public void removeByAccountNumber(String accountNumber) throws Exception {
        Bank bank = entityManager.find(Bank.class, accountNumber);
        bank.setDeleted(true);
        entityManager.merge(bank);
    }

    @Transactional
    @Override
    public List<Bank> findAll() throws Exception {
        TypedQuery<Bank> query = entityManager.createQuery("SELECT oo FROM bankEntity oo WHERE oo.deleted=false ", Bank.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Bank> findByName(String name) throws Exception {
        TypedQuery<Bank> query = entityManager.createQuery("SELECT oo FROM bankEntity oo WHERE oo.name = :name AND oo.deleted=false", Bank.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Bank> findByBranchCode(int branchCode) throws Exception {
        TypedQuery<Bank> query = entityManager.createQuery("SELECT oo FROM bankEntity oo WHERE oo.branchCode=:branchCode AND oo.deleted=false ", Bank.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Bank> findByBranchName(String branchName) throws Exception {
        TypedQuery<Bank> query = entityManager.createQuery("SELECT oo FROM bankEntity oo WHERE oo.branchName=:branchName AND oo.deleted=false", Bank.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Bank> findByAccountType(String accountType) throws Exception {
        TypedQuery<Bank> query = entityManager.createQuery("SELECT oo FROM bankEntity oo WHERE oo.accountType=:accountType AND oo.deleted=false", Bank.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Bank>  findByAccountOwner(String username) throws Exception {
        TypedQuery<Bank> query = entityManager.createQuery("SELECT oo FROM bankEntity oo WHERE oo.accountOwner.username = :username AND oo.deleted=false", Bank.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<Bank> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(Bank.class, id));
    }

    @Transactional
    @Override
    public Optional<Bank> findByAccountNumber(String accountNumber) throws Exception {
        return Optional.ofNullable(entityManager.find(Bank.class, accountNumber));
    }
}
