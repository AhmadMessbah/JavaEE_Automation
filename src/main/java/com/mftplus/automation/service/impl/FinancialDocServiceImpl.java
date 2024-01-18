package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.FinancialDoc;
import com.mftplus.automation.model.FinancialTransaction;
import com.mftplus.automation.model.Section;
import com.mftplus.automation.model.User;
import com.mftplus.automation.model.enums.FinancialDocType;
import com.mftplus.automation.service.FinancialDocService;
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
public class FinancialDocServiceImpl implements FinancialDocService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager  entityManager;

    @Transactional
    @Override
    public void save(FinancialDoc financialDoc) throws Exception {
        entityManager.persist(financialDoc);
    }

    @Transactional
    @Override
    public void edit(FinancialDoc financialDoc) throws Exception {
        entityManager.merge(financialDoc);
    }

    @Transactional
    @Override
    public void remove(FinancialDoc financialDoc) throws Exception {
        entityManager.remove(financialDoc);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        FinancialDoc financialDoc = entityManager.find(FinancialDoc.class, id);
        entityManager.remove(financialDoc);
    }

    @Transactional
    @Override
    public void removeByNumberDoc(Long numberDoc) throws Exception {
        FinancialDoc financialDoc=entityManager.find(FinancialDoc.class,numberDoc);
        entityManager.remove(financialDoc);
    }

    @Transactional
    @Override
    public Optional<FinancialDoc> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(FinancialDoc.class,id));
    }

    @Transactional
    @Override
    public Optional<FinancialDoc> findByNumberDoc(Long numberDoc) throws Exception {
        return Optional.ofNullable(entityManager.find(FinancialDoc.class,numberDoc));
    }

    @Transactional
    @Override
    public Optional<FinancialDoc> findByFinancialTransaction(Long id) throws Exception {
        FinancialTransaction financialTransaction=entityManager.find(FinancialTransaction.class,id);
        return Optional.ofNullable(entityManager.find(FinancialDoc.class,financialTransaction));
    }

    @Transactional
    @Override
    public List<FinancialDoc> findAll() throws Exception {
        TypedQuery<FinancialDoc> query= entityManager.createQuery("SELECT oo FROM financialDocEntity oo",FinancialDoc.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<FinancialDoc> findByDateTime(LocalDateTime dateTime) throws Exception {
        TypedQuery<FinancialDoc> query=entityManager.createQuery("SELECT oo FROM financialDocEntity oo WHERE oo.dateTime=:dateTime",FinancialDoc.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<FinancialDoc> findByType(FinancialDocType type) throws Exception {
        TypedQuery<FinancialDoc> query=entityManager.createQuery("SELECT oo FROM financialDocEntity oo WHERE oo.type=:type",FinancialDoc.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<FinancialDoc> findBySection(Long id) throws Exception {
        TypedQuery<FinancialDoc> query=entityManager.createQuery("SELECT oo FROM financialDocEntity oo WHERE oo.section.id=:id",FinancialDoc.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<FinancialDoc> findBySender(String username) throws Exception {
        TypedQuery<FinancialDoc> query=entityManager.createQuery("SELECT oo FROM financialDocEntity oo WHERE oo.sender.username=:username",FinancialDoc.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<FinancialDoc> findByReceiver(String username) throws Exception {
        TypedQuery<FinancialDoc> query=entityManager.createQuery("SELECT oo FROM financialDocEntity oo WHERE oo.receiver.username=:username",FinancialDoc.class);
        return query.getResultList();
    }
}
