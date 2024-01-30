package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.CheckPayment;
import com.mftplus.automation.service.CheckPaymentService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@SessionScoped
public class CheckPaymentServiceImp implements CheckPaymentService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(CheckPayment checkPayment) throws Exception {
        entityManager.persist(checkPayment);
    }

    @Transactional
    @Override
    public void edit(CheckPayment checkPayment) throws Exception {
        entityManager.merge(checkPayment);
    }

    @Transactional
    @Override
    public void remove(CheckPayment checkPayment) throws Exception {
        checkPayment.setDeleted(true);
        entityManager.merge(checkPayment);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        CheckPayment checkPayment =entityManager.find(CheckPayment.class,id);
        checkPayment.setDeleted(true);
        entityManager.merge(checkPayment);
    }

    @Transactional
    @Override
    public void removeByCheckNumber(String checkNumber) throws Exception {
        CheckPayment checkPayment =entityManager.find(CheckPayment.class,checkNumber);
        checkPayment.setDeleted(true);
        entityManager.merge(checkPayment);
    }

    @Transactional
    @Override
    public List<CheckPayment> findAll() throws Exception {
        TypedQuery<CheckPayment> query = entityManager.createQuery("SELECT oo FROM checkPaymentEntity oo WHERE oo.deleted=false ", CheckPayment.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<CheckPayment> findByCheckDueDate(LocalDateTime checkDueDate) throws Exception {
        TypedQuery<CheckPayment> query = entityManager.createQuery("SELECT oo FROM checkPaymentEntity oo WHERE oo.checkDueDate=:checkDueDate AND oo.deleted=false ", CheckPayment.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<CheckPayment> findByCashDeskNumber(int cashDeskNumber) throws Exception {
        return Optional.ofNullable(entityManager.find(CheckPayment.class, cashDeskNumber));
    }

    @Transactional
    @Override
    public Optional<CheckPayment> findByCheckNumber(String checkNumber) throws Exception {
        return Optional.ofNullable(entityManager.find(CheckPayment.class, checkNumber));
    }

    @Transactional
    @Override
    public Optional<CheckPayment> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(CheckPayment.class, id));
    }
}
