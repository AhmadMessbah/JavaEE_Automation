package com.mftplus.automation.service.impl;
import com.mftplus.automation.model.StuffTransaction;
import com.mftplus.automation.service.StuffTransactionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
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
        return query.getResultList();
    }

    @Override
    public Optional<StuffTransaction> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(StuffTransaction.class, id));
    }



    public List<StuffTransaction> findByTransactionType(String transactionType ) throws Exception {
        TypedQuery<StuffTransaction> query = entityManager
                .createQuery("select oo from stuffTransactionEntity oo where  oo.transactionType=:transactionType",StuffTransaction.class);
         return query.getResultList();

    }

    public List<StuffTransaction> findByDate(LocalDate datetime) throws Exception {
        TypedQuery<StuffTransaction> query = entityManager.createQuery("select oo from stuffTransactionEntity oo where oo.dateTime=:datetime", StuffTransaction.class);
        query.setParameter(String.valueOf(datetime),"datetime");
        return query.getResultList();
    }

}
