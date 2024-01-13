package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Section;
import com.mftplus.automation.service.SectionService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Transient;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@SessionScoped
public class SectionService implements SectionServiceImp, Serializable {
    @PersistenceContext(unitName = "")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Section section) throws Exception {
        entityManager.persist(section);

    }

    @Override
    @Transactional
    public void edit(Section section) throws Exception {
        entityManager.merge(section);

    }

    @Override
    @Transactional
    public void remove(Section section) throws Exception {
        section = entityManager.find(Section.class, section.getId());
        section.setDeleted(true);
        entityManager.merge(section);

    }

    @Override
    public void removeById(Long id) throws Exception {
        Section section = entityManager.find(Section.class, id);
        section.setDeleted(true);
        entityManager.merge(section);

    }

    @Override
    public List<Section> findAll() throws Exception {
        TypedQuery<Section> query = entityManager.createQuery("select oo from sectionEntity oo", Section.class);
        return query.getResultList();
    }

    @Override
    public Optional<Section> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(Section.class, id));
    }
     public Optional<Section> findByTitle(String title)throws Exception{
         TypedQuery<Section> query = (TypedQuery<Section>) entityManager.createQuery("select s from sectionEntity  s where s.title = :title");
         query.setParameter("title", title);
         List<Section> result = query.getResultList();
         return Optional.ofNullable((result.isEmpty()) ? null : result.get(0));
     }
}

