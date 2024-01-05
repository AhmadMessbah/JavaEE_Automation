package com.mftplus.automation.service;

import com.mftplus.automation.model.StuffStorage;
import com.mftplus.automation.service.impl.StuffStorageService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class StuffStorageServiceImpl implements StuffStorageService {
    @Override
    public void save(StuffStorage stuffStorage) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(stuffStorage);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void edit(StuffStorage stuffStorage) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(stuffStorage);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void remove(StuffStorage stuffStorage) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(stuffStorage);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void removeById(Long id) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        StuffStorage stuffStorage = entityManager.find(StuffStorage.class, id);
        entityManager.remove(stuffStorage);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public List<StuffStorage> findAll() throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();

        TypedQuery<StuffStorage> query = entityManager.createQuery("select p from stuffStorageEntity p", StuffStorage.class);
        List<StuffStorage> stuffStorageList = query.getResultList();

        entityManager.close();
        return stuffStorageList;
    }

    @Override
    public Optional<StuffStorage> findById(Long id) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        Optional<StuffStorage> stuffStorage = Optional.ofNullable(entityManager.find(StuffStorage.class, id));
        entityManager.close();
        return stuffStorage;
    }
}
