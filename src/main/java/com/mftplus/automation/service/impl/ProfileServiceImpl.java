package com.mftplus.automation.service.impl;

import com.mftplus.automation.common.JPA;
import com.mftplus.automation.model.Profile;
import com.mftplus.automation.service.ProfileService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class ProfileServiceImpl implements ProfileService {
    @Override
    public void save(Profile profile) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(profile);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void edit(Profile profile) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.merge(profile);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void remove(Profile profile) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(profile);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public void removeById(Long id) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Profile profile = entityManager.find(Profile.class, id);
        entityManager.remove(profile);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public List<Profile> findAll() throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        TypedQuery<Profile> query = entityManager.createQuery("select p from profileEntity p", Profile.class);
        List<Profile> profileList = query.getResultList();

        entityManager.close();
        return profileList;
    }

    @Override
    public Optional<Profile> findById(Long id) throws Exception {
        EntityManager entityManager = JPA.getJpa().getEntityManager();
        Optional<Profile> profile = Optional.ofNullable(entityManager.find(Profile.class, id));
        entityManager.close();
        return profile;
    }
}
