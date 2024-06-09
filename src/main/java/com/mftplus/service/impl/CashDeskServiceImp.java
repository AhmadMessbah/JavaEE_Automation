package com.mftplus.service.impl;

import com.mftplus.controller.exception.NoContentException;
import com.mftplus.model.CashDesk;
import com.mftplus.service.CashDeskService;
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
        log.info("CashDeskService - save");
        entityManager.persist(cashDesk);
    }

    @Transactional
    @Override
    public void edit(CashDesk cashDesk) throws NoContentException {
        Optional<CashDesk> optionalCashDesk = Optional.ofNullable(entityManager.find(CashDesk.class, cashDesk.getId()));

        if (optionalCashDesk.isPresent()) {
            entityManager.merge(cashDesk);
        } else {
            throw new NoContentException("Cash Desk with id : " + cashDesk.getId() + " not found !");
        }
    }

    @Transactional
    @Override
    public void remove(CashDesk cashDesk) throws Exception {
        cashDesk = entityManager.find(CashDesk.class, cashDesk.getId());
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

    @Override
    public void removeByCashDeskNumber(int cashDeskNumber) throws Exception {

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
        query.setParameter("name",name);
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
    public Optional<CashDesk> findById(Long id) throws NoContentException {
        Optional<CashDesk> optional = Optional.ofNullable(entityManager.find(CashDesk.class, id));
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("Cash Desk with id : " + id + "not found !");
        }
    }
}
