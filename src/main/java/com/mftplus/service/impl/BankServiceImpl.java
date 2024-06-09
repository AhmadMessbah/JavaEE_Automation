package com.mftplus.service.impl;

import com.mftplus.controller.exception.NoContentException;
import com.mftplus.model.Bank;
import com.mftplus.service.BankService;
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
        log.info("BankService - save");
        entityManager.persist(bank);
    }

    @Transactional
    @Override
    public void edit(Bank bank) throws NoContentException {
        Optional<Bank> optionalBank = Optional.ofNullable(entityManager.find(Bank.class, bank.getId()));

        if (optionalBank.isPresent()) {
            entityManager.merge(bank);
        } else {
            throw new NoContentException("Bank with id : " + bank.getId() + " not found !");
        }
    }

    @Transactional
    @Override
    public void remove(Bank bank) throws Exception {
        bank = entityManager.find(Bank.class, bank.getId());
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
    public List<Bank> findAll() throws Exception {
        TypedQuery<Bank> query = entityManager.createQuery("SELECT oo FROM bankEntity oo WHERE oo.deleted=false ", Bank.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Bank> findByName(String name) throws Exception {
        TypedQuery<Bank> query = entityManager.createQuery("SELECT oo FROM bankEntity oo WHERE oo.name = :name AND oo.deleted=false", Bank.class);
        query.setParameter("name",name);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Bank> findByBranchCode(Long branchCode) {
        TypedQuery<Bank> query = entityManager.createQuery("SELECT oo FROM bankEntity oo WHERE oo.branchCode=:branchCode AND oo.deleted=false ", Bank.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Bank> findByBranchName(String branchName) {
        TypedQuery<Bank> query = entityManager.createQuery("SELECT oo FROM bankEntity oo WHERE oo.branchName=:branchName AND oo.deleted=false", Bank.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<Bank> findById(Long id) throws NoContentException {
        Optional<Bank> optional = Optional.ofNullable(entityManager.find(Bank.class, id));
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Bank with id : " + id + "not found !");
        }
    }

    @Transactional
    @Override
    public Optional<Bank> findByAccountNumber(String accountNumber) {
        return Optional.ofNullable(entityManager.find(Bank.class, accountNumber));
    }
}