package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.LetterReference;
import com.mftplus.automation.service.LetterReferenceService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class LetterReferenceServiceImpl implements LetterReferenceService {
    @Override
    public void save(LetterReference letterReference) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(letterReference);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void edit(LetterReference letterReference) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(letterReference);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void remove(LetterReference letterReference) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(letterReference);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void removeById(Long id) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        LetterReference letterReference = entityManager.find(LetterReference.class, id);
        entityManager.remove(letterReference);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public List<LetterReference> findAll() throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();

        TypedQuery<LetterReference> query = entityManager.createQuery("select p from letterReferenceEntity p", LetterReference.class);
        List<LetterReference> letterReferenceList = query.getResultList();

        entityManager.close();
        return letterReferenceList;
    }

    @Override
    public Optional<LetterReference> findById(Long id) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        Optional<LetterReference> letterReference = Optional.ofNullable(entityManager.find(LetterReference.class, id));
        entityManager.close();
        return letterReference;
    }
}
