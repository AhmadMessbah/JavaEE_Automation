package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Letter;
import com.mftplus.automation.service.LetterService;
import com.mftplus.automation.common.JPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class LetterServiceImpl implements LetterService {
    @Override
    public void save(Letter letter) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(letter);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void edit(Letter letter) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(letter);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void remove(Letter letter) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(letter);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void removeById(Long id) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Letter letter = entityManager.find(Letter.class, id);
        entityManager.remove(letter);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public List<Letter> findAll() throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();

        TypedQuery<Letter> query = entityManager.createQuery("select p from letterEntity p", Letter.class);
        List<Letter> letterList = query.getResultList();

        entityManager.close();
        return letterList;
    }

    @Override
    public Optional<Letter> findById(Long id) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        Optional<Letter> letter = Optional.ofNullable(entityManager.find(Letter.class, id));
        entityManager.close();
        return letter;
    }
}
