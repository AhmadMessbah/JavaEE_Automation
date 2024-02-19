package com.mftplus.automation.service.impl;


import com.mftplus.automation.model.StuffStorage;
import com.mftplus.automation.service.StuffStorageService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@SessionScoped
public class StuffStorageServiceImpl implements StuffStorageService, Serializable {
    @PersistenceContext(name = "automation")
    private  EntityManager entityManager;

    @Transactional
    @Override
    public void save(StuffStorage stuffStorage) throws Exception {
        entityManager.persist(stuffStorage);
    }

    @Transactional
    @Override
    public void edit(StuffStorage stuffStorage) throws Exception {
        entityManager.merge(stuffStorage);

    }
    @Transactional
    @Override
    public void remove(StuffStorage stuffStorage) throws Exception {
        entityManager.remove(stuffStorage);
    }
    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        StuffStorage stuffStorage = entityManager.find(StuffStorage.class, id);
        stuffStorage.setDeleted(true);
        entityManager.merge(stuffStorage);

    }

    @Override
    public List<StuffStorage> findAll() throws Exception {
        TypedQuery<StuffStorage> query = entityManager.createQuery(
                "select p from StuffStorageEntity p where p.deleted=false", StuffStorage.class);
        return query.getResultList();
    }

    @Override
    public Optional<StuffStorage> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(StuffStorage.class, id));
    }

    public Optional<StuffStorage> findByName (String name) throws Exception{
        TypedQuery<StuffStorage> query = (TypedQuery<StuffStorage>)entityManager.createQuery(
                "select ss from StuffStorageEntity ss where ss.name=:name", StuffStorage.class);
        query.setParameter("name",name);
        Optional<StuffStorage> result = Optional.ofNullable(query.getSingleResult());
        return Optional.ofNullable((result.isEmpty()) ? null: result.get());
    }



}
