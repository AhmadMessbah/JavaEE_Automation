package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Stuff;
import com.mftplus.automation.service.StuffService;
import com.mftplus.automation.common.JPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class StuffServiceImpl implements StuffService {
    @Override
    public void save(Stuff stuff) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(stuff);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void edit(Stuff stuff) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(stuff);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void remove(Stuff stuff) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(stuff);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void removeById(Long id) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Stuff stuff = entityManager.find(Stuff.class, id);
        entityManager.remove(stuff);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public List<Stuff> findAll() throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();

        TypedQuery<Stuff> query = entityManager.createQuery("select p from stuffEntity p", Stuff.class);
        List<Stuff> stuffList = query.getResultList();

        entityManager.close();
        return stuffList;
    }

    @Override
    public Optional<Stuff> findById(Long id) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        Optional<Stuff> stuff = Optional.ofNullable(entityManager.find(Stuff.class, id));
        entityManager.close();
        return stuff;
    }
}
