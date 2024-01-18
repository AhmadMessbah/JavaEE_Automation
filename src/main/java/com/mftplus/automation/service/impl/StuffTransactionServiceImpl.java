package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.StuffTransaction;
import com.mftplus.automation.model.User;
import com.mftplus.automation.service.StuffTransactionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;


public class StuffTransactionServiceImpl implements StuffTransactionService {
    @PersistenceContext(name = "automation")
    private EntityManager entityManager;


    @Override
    public void save(StuffTransaction stuffTransaction) throws Exception {
        entityManager.persist(stuffTransaction);
    }

    @Override
    public void edit(StuffTransaction stuffTransaction) throws Exception {
        entityManager.merge(stuffTransaction);
    }

    @Override
    public void remove(StuffTransaction stuffTransaction) throws Exception {
        entityManager.remove(stuffTransaction);
    }

    @Override
    public void removeById(Long id) throws Exception {
        StuffTransaction stuffTransaction = entityManager.find(StuffTransaction.class, id);
        entityManager.remove(stuffTransaction);
    }

    @Override
    public List<StuffTransaction> findAll() throws Exception {
        TypedQuery<StuffTransaction> query = entityManager.createQuery("select p from stuffTransactionEntity p", StuffTransaction.class);
        List<StuffTransaction> stuffTransactionslist = query.getResultList();
        return stuffTransactionslist;
    }

    @Override
    public Optional<StuffTransaction> findById(Long id) throws Exception {
        Optional<StuffTransaction> stuffTransactionId = Optional.ofNullable(entityManager.find(StuffTransaction.class, id));
        return stuffTransactionId;
    }
    public Optional<User> findByEnteredBy(Long id){
        Optional<User> userEnteredBy = Optional.ofNullable(entityManager.find(User.class,id));
        return userEnteredBy;
    }

    public Optional<User> findByExitedBy(Long id){
        Optional<User> userExitedBy = Optional.ofNullable(entityManager.find(User.class,id));
        return userExitedBy;
    }
}
