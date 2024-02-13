package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Stuff;
import com.mftplus.automation.service.StuffService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@SessionScoped
public class StuffServiceImpl implements StuffService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Stuff stuff) throws Exception {
        entityManager.persist(stuff);

    }

    @Override
    @Transactional
    public void edit(Stuff stuff) throws Exception {
        entityManager.merge(stuff);
    }

    @Override
    @Transactional
    public void remove(Stuff stuff) throws Exception {
        stuff= entityManager.find(Stuff.class,stuff.getId());
        stuff.setDeleted(true);
        entityManager.merge(stuff);

    }

    @Override
    @Transactional
    public void removeById(Long id) throws Exception {
        Stuff stuff = entityManager.find(Stuff.class, id);
        stuff.setDeleted(true);
        entityManager.merge(stuff);
    }

    @Override
    public List<Stuff> findAll() throws Exception {
        TypedQuery<Stuff> query = entityManager.createQuery("select p from stuffEntity p where p.deleted=false ", Stuff.class);
        return query.getResultList();
    }

    @Override
    public Optional<Stuff> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(Stuff.class, id));
    }

    public Optional<Stuff> findByName(String name) throws Exception{
        TypedQuery<Stuff>query=(TypedQuery<Stuff>) entityManager.createQuery("select oo from stuffEntity  oo where oo.name=:name");
        query.setParameter("name",name);
        List<Stuff>result=query.getResultList();
        return Optional.ofNullable((result.isEmpty()) ? null: result.get(0));
    }

    public Optional<Stuff> findByModel(String model) throws Exception{
        TypedQuery<Stuff> query = entityManager.createQuery("select oo from stuffEntity oo where oo.model=:model", Stuff.class);
        query.setParameter("model",model);
        List<Stuff> result = query.getResultList();
        return Optional.ofNullable((result.isEmpty()) ? null : result.get(0));
    }


    public Optional<Stuff> findByStatus(String status) throws Exception{
        TypedQuery<Stuff> query = entityManager.createQuery("select oo from stuffEntity oo where oo.status=:status", Stuff.class);
        query.setParameter("status",status);
        List<Stuff> result = query.getResultList();
        return Optional.ofNullable((result.isEmpty()) ? null : result.get(0));

    }

    public Optional<Stuff> findByPrice(String price) throws Exception {
        TypedQuery<Stuff> query = entityManager.createQuery("select p from stuffEntity p where p.price= :price", Stuff.class);
        query.setParameter("price",price);
        List<Stuff> result = query.getResultList();
        return Optional.ofNullable((result.isEmpty()) ? null : result.get(0));
    }


}
