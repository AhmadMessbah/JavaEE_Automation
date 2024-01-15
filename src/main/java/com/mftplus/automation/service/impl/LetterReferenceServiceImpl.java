package com.mftplus.automation.service.impl;

import com.mftplus.letter.model.entity.Letter;
import com.mftplus.letter.model.entity.Reference;
import com.mftplus.letter.model.service.interfaces.ReferenceService;
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
public class LetterReferenceServiceImpl implements LetterReferenceService {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;
    
 @Transactional
    @Override
    public LetterReference save(LetterReference letterReference) throws Exception {
        entityManager.persist(reference);
        return reference;
    }

    @Transactional
    @Override
    public LetterReference edit(LetterReference letterReference) throws Exception {
        entityManager.merge(reference);
        return reference;
    }

    @Transactional
    @Override
    public LetterReference remove(LetterReference letterReference) throws Exception {
        entityManager.remove(reference);
        return reference;
    }

    @Transactional
    @Override
    public LetterReference removeById(Long id) throws Exception {
        LetterReference reference = entityManager.find(Reference.class,id);
        entityManager.remove(reference);
        return reference;
    }

    @Override
    public Optional<LetterReference> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(LetterReference.class, id));
    }

    @Override
    public List<LetterReference> findAll() throws Exception {
        TypedQuery<LetterReference> query = entityManager.createQuery("select oo from referenceEntity oo", LetterReference.class);
        return query.getResultList();
    }

    @Override
    public Optional<LetterReference> findByReferenceSenderOrReceiver(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(LetterReference.class, id));
    }

    @Override
    public List<LetterReference> findByRefDate(LocalDateTime refDateAndTime) throws Exception {
        TypedQuery<LetterReference> query = entityManager.createQuery("select oo from referenceEntity oo where oo.refDateAndTime=:refDateAndTime", LetterReference.class);
        query.setParameter(String.valueOf(refDateAndTime),"refDateAndTime");
        return query.getResultList();
    }

    @Override
    public Optional<LetterReference> findByLetterId(Letter letterId) throws Exception {
        return Optional.ofNullable(entityManager.find(LetterReference.class, letterId));
    }

    @Override
    public List<LetterReference> findByValidate(Boolean validate) throws Exception {
        TypedQuery<LetterReference> query = entityManager.createQuery("select oo from referenceEntity oo where oo.validate=:validate", LetterReference.class);
        query.setParameter(String.valueOf(validate),"validate");
        return query.getResultList();
    }

    @Override
    public List<LetterReference> findByParaph(String paraph) throws Exception {
        TypedQuery<LetterReference> query = entityManager.createQuery("select oo from referenceEntity oo where oo.paraph=:paraph", LetterReference.class);
        query.setParameter(paraph,"paraph");
        return query.getResultList();
    }

    @Override
    public List<LetterReference> findByPriority(List<Reference> priority) throws Exception {
        TypedQuery<LetterReference> query = entityManager.createQuery("select oo from referenceEntity oo where oo.priority=:priority", LetterReference.class);
        query.setParameter(String.valueOf(priority),"priority");
        return query.getResultList();
    }

    @Override
    public List<LetterReference> findByStatus(Boolean status) throws Exception {
        TypedQuery<LetterReference> query = entityManager.createQuery("select oo from referenceEntity oo where oo.status=:status", LetterReference.class);
        query.setParameter(String.valueOf(status),"status");
        return query.getResultList();
    }
}

