package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Stuff;
import com.mftplus.automation.service.StuffService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class StuffServiceImpl implements StuffService {

    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;
    @Override
    public void save(Stuff stuff) throws Exception {
        entityManager.persist(stuff);

    }

    @Override
    public void edit(Stuff stuff) throws Exception {
        entityManager.merge(stuff);
    }

    @Override
    public void remove(Stuff stuff) throws Exception {
        entityManager.remove(stuff);

    }

    @Override
    public void removeById(Long id) throws Exception {
        Stuff stuff = entityManager.find(Stuff.class, id);
        entityManager.remove(stuff);
    }

    @Override
    public List<Stuff> findAll() throws Exception {

        TypedQuery<Stuff> query = entityManager.createQuery("select p from stuffEntity p", Stuff.class);
        return query.getResultList();
    }

    @Override
    public Optional<Stuff> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(Stuff.class, id));
    }

    public Optional<Stuff> findByName(String name) throws Exception{
        return Optional.ofNullable(entityManager.find(Stuff.class,name));
    }

    public Optional<Stuff> findByModel(String model) throws Exception{
        TypedQuery<Stuff> query = entityManager.createQuery("select oo from stuffEntity oo where oo.model=:model", Stuff.class);
        query.setParameter("model",model);
        List<Stuff> stuffModel = query.getResultList();

//        return Optional.ofNullable(stuffModel.isEmpty())? null : stuffModel.get(0);
        return Optional.ofNullable((Stuff) stuffModel);
    }


    public Optional<Stuff> findByStatus(String status) throws Exception{
        return Optional.ofNullable(entityManager.find(Stuff.class,status));
    }

    public List<Stuff> findByPrice() throws Exception {
        TypedQuery<Stuff> query = entityManager.createQuery("select p from stuffEntity p where p.price= :price", Stuff.class);
        return query.getResultList();
    }


}
