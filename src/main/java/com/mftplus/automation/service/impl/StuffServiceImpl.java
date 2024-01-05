package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Stuff;
import com.mftplus.automation.service.StuffService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class StuffServiceImpl implements StuffService {

    @PersistenceContext(unitName = "atomation")
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
        List<Stuff> stuffList = query.getResultList();
        return stuffList;
    }

    @Override
    public Optional<Stuff> findById(Long id) throws Exception {
        Optional<Stuff> stuff = Optional.ofNullable(entityManager.find(Stuff.class, id));
        return stuff;
    }
}
