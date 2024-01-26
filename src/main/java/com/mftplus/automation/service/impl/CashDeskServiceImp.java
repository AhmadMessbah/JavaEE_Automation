package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.CashDesk;
import com.mftplus.automation.service.CashDeskService;
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
public class CashDeskServiceImp implements CashDeskService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(CashDesk cashDesk) throws Exception {
        entityManager.persist(cashDesk);
    }

    @Transactional
    @Override
    public void edit(CashDesk cashDesk) throws Exception {
        entityManager.merge(cashDesk);
    }

    @Transactional
    @Override
    public void remove(CashDesk cashDesk) throws Exception {
        cashDesk.setDeleted(true);
        entityManager.merge(cashDesk);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        CashDesk cashDesk = entityManager.find(CashDesk.class, id);
        cashDesk.setDeleted(true);
        entityManager.merge(cashDesk);
    }

    @Transactional
    @Override
    public void removeByCashDeskNumber(int cashDeskNumber) throws Exception {
        CashDesk cashDesk = entityManager.find(CashDesk.class, cashDeskNumber);
        cashDesk.setDeleted(true);
        entityManager.merge(cashDesk);
    }

    @Transactional
    @Override
    public List<CashDesk> findAll() throws Exception {
        TypedQuery<CashDesk> query = entityManager.createQuery("SELECT oo FROM cashDeskEntity oo WHERE oo.deleted=false ", CashDesk.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<CashDesk> findByName(String name) throws Exception {
        TypedQuery<CashDesk> query = entityManager.createQuery("SELECT oo FROM cashDeskEntity oo WHERE oo.name=:name AND oo.deleted=false ", CashDesk.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<CashDesk> findByCashDeskNumber(int cashDeskNumber) throws Exception {
        return Optional.ofNullable(entityManager.find(CashDesk.class, cashDeskNumber));
    }

    @Transactional
    @Override
    public Optional<CashDesk> findByCashier(String username) throws Exception {
        return Optional.ofNullable(entityManager.find(CashDesk.class, username));
    }

    @Transactional
    @Override
    public Optional<CashDesk> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(CashDesk.class, id));
    }
}
