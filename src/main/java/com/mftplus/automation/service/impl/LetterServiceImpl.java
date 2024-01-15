package com.mftplus.automation.service.impl;


import com.mftplus.letter.model.entity.Letter;
import com.mftplus.letter.model.service.interfaces.LetterService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SessionScoped
@Slf4j
public class LetterServiceImpl implements LetterService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Letter letter) throws Exception {
        entityManager.persist(letter);
    }

    @Transactional
    @Override
    public void edit(Letter letter) throws Exception {
        entityManager.merge(letter);
    }

    @Transactional
    @Override
    public void remove(Letter letter) throws Exception {
        entityManager.remove(letter);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        Letter letter = entityManager.find(Letter.class,id);
        entityManager.remove(letter);
    }

    @Transactional
    @Override
    public Optional<Letter> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(Letter.class, id));
    }

    @Transactional
    @Override
    public List<Letter> findAll() throws Exception {
        TypedQuery<Letter> query = entityManager.createQuery("select oo from letterEntity oo", Letter.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Letter> findByTitle(String title) throws Exception {
        TypedQuery<Letter> query = entityManager.createQuery("select oo from letterEntity oo where oo.title=:title", Letter.class);
        query.setParameter(title,"title");
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Letter> findByContext(String context) throws Exception {
        TypedQuery<Letter> query = entityManager.createQuery("select oo from letterEntity oo where oo.context=:context", Letter.class);
        query.setParameter(context,"context");
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Letter> findByDate(LocalDate date) throws Exception {
        TypedQuery<Letter> query = entityManager.createQuery("select oo from letterEntity oo where oo.date=:date", Letter.class);
        query.setParameter(String.valueOf(date),"date");
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<Letter> findByRegisterNumber(Long registerNumber) throws Exception {
        return Optional.ofNullable(entityManager.find(Letter.class, registerNumber));
    }

    @Transactional
    @Override
    public List<Letter> findByRegisterDate(LocalDateTime dateTime) throws Exception {
        TypedQuery<Letter> query = entityManager.createQuery("select oo from letterEntity oo where oo.registerDateAndTime=:dateTime", Letter.class);
        query.setParameter(String.valueOf(dateTime),"dateTime");
        return query.getResultList();
    }

    @Override
    public List<Letter> findBySenderNameAndTitle(String senderName, String senderTitle) throws Exception {
        TypedQuery<Letter> query = entityManager.createQuery("select oo from letterEntity oo where oo.senderName=:senderName and oo.senderTitle=:senderTitle", Letter.class);
        query.setParameter(senderName,"senderName");
        query.setParameter(senderTitle,"senderTitle");
        return query.getResultList();
    }

    @Override
    public List<Letter> findBySectionId(Long sectionId) throws Exception {
        TypedQuery<Letter> query = entityManager.createQuery("select oo from letterEntity oo where oo.user.sectionId=:sectionId", Letter.class);
        query.setParameter(String.valueOf(sectionId),"sectionId");
        return query.getResultList();
    }

}
