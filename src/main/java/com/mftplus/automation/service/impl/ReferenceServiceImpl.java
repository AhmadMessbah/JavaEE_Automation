package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Reference;
import com.mftplus.automation.model.enums.ReferencePriority;
import com.mftplus.automation.service.ReferenceService;
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

@SessionScoped
@Slf4j
public class ReferenceServiceImpl implements ReferenceService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Reference reference) throws Exception {
        System.out.println(reference);
        entityManager.persist(reference);
    }

    @Transactional
    @Override
    public void edit(Reference reference) throws Exception {
        entityManager.merge(reference);
    }

    @Transactional
    @Override
    public void remove(Reference reference) throws Exception {
        reference = entityManager.find(Reference.class, reference.getId());
        reference.setDeleted(true);
        entityManager.merge(reference);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        Reference reference = entityManager.find(Reference.class, id);
        reference.setDeleted(true);
        entityManager.merge(reference);
    }

    @Override
    public Optional<Reference> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(Reference.class, id));
    }

    @Override
    public List<Reference> findAll() throws Exception {
        TypedQuery<Reference> query = entityManager.createQuery("select oo from referenceEntity oo where oo.deleted=false", Reference.class);
        return query.getResultList();
    }

    @Override
    public Optional<Reference> findByReferenceSenderOrReceiver(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(Reference.class, id));
    }

    @Override
    public List<Reference> findByRefDate(LocalDateTime refDateAndTime) throws Exception {
        TypedQuery<Reference> query = entityManager.createQuery("select oo from referenceEntity oo where oo.refDateAndTime=:refDateAndTime", Reference.class);
        query.setParameter(String.valueOf(refDateAndTime),"refDateAndTime");
        return query.getResultList();
    }

    @Override
    public List<Reference> findByLetterId(Long letterId) throws Exception {
        TypedQuery<Reference> query = entityManager.createQuery("select oo from referenceEntity oo where oo.letterId.id=:letterId", Reference.class);
        query.setParameter(String.valueOf(letterId),"letterId");
        return query.getResultList();
    }

    @Override
    public List<Reference> findByValidate(Boolean validate) throws Exception {
        TypedQuery<Reference> query = entityManager.createQuery("select oo from referenceEntity oo where oo.validate=:validate", Reference.class);
        query.setParameter(String.valueOf(validate),"validate");
        return query.getResultList();
    }

    @Override
    public List<Reference> findByParaph(String paraph) throws Exception {
        TypedQuery<Reference> query = entityManager.createQuery("select oo from referenceEntity oo where oo.paraph=:paraph", Reference.class);
        query.setParameter(paraph,"paraph");
        return query.getResultList();
    }

    //todo input needs to rethink
    @Override
    public List<Reference> findByPriority(ReferencePriority priority) throws Exception {
        TypedQuery<Reference> query = entityManager.createQuery("select oo from referenceEntity oo where oo.priority=:priority", Reference.class);
        query.setParameter(String.valueOf(priority),"priority");
        return query.getResultList();
    }

    @Override
    public List<Reference> findByStatus(Boolean status) throws Exception {
        TypedQuery<Reference> query = entityManager.createQuery("select oo from referenceEntity oo where oo.status=:status", Reference.class);
        query.setParameter(String.valueOf(status),"status");
        return query.getResultList();
    }
}
