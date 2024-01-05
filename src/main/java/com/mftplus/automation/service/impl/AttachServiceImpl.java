package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Attach;
import com.mftplus.automation.service.AttachService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Slf4j
@SessionScoped
public class AttachServiceImpl implements AttachService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Attach attach) throws Exception {
        entityManager.persist(attach);
    }

    @Transactional
    @Override
    public void edit(Attach attach) throws Exception {
        entityManager.merge(attach);
    }

    @Transactional
    @Override
    public void remove(Attach attach) throws Exception {
        entityManager.remove(attach);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        Attach attach = entityManager.find(Attach.class, id);
        entityManager.remove(attach);
    }

    @Transactional
    @Override
    public List<Attach> findAll() throws Exception {
        TypedQuery<Attach> query = entityManager.createQuery("select p from attachEntity p", Attach.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<Attach> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(Attach.class, id));
    }
}
