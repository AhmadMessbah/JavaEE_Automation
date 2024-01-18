package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.StuffStorage;
import com.mftplus.automation.model.User;
import com.mftplus.automation.service.StuffStorageService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


import java.util.List;
import java.util.Optional;


public class StuffStorageServiceImpl implements StuffStorageService {
    @PersistenceContext(name = "automation")
    private  EntityManager entityManager;

    @Override
    public void save(StuffStorage stuffStorage) throws Exception {
        entityManager.persist(stuffStorage);
    }

    @Override
    public void edit(StuffStorage stuffStorage) throws Exception {
        entityManager.merge(stuffStorage);

    }

    @Override
    public void remove(StuffStorage stuffStorage) throws Exception {
        entityManager.remove(stuffStorage);
    }

    @Override
    public void removeById(Long id) throws Exception {
        StuffStorage stuffStorage = entityManager.find(StuffStorage.class, id);
        entityManager.remove(stuffStorage);

    }

    @Override
    public List<StuffStorage> findAll() throws Exception {
        TypedQuery<StuffStorage> query = entityManager.createQuery("select p from StuffStorageEntity p", StuffStorage.class);
        List<StuffStorage> stuffStorageList = query.getResultList();
        return stuffStorageList;
    }

    @Override
    public Optional<StuffStorage> findById(Long id) throws Exception {
        Optional<StuffStorage> stuffStorage = Optional.ofNullable(entityManager.find(StuffStorage.class, id));
        return stuffStorage;
    }

    public List<StuffStorage> findByName (String name) throws Exception{
        TypedQuery<StuffStorage> query = entityManager.createQuery("select ss from StuffStorageEntity ss where ss.name=:name", StuffStorage.class);
        query.setParameter("name",name);
        List<StuffStorage> stuffStorageList = query.getResultList();
        return stuffStorageList;
    }



}
