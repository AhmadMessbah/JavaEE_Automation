package com.mftplus.automation.service;

import com.example.javaeelast2.model.entity.Section;
import com.example.javaeelast2.model.service.imp.SectionServiceImp;
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
    @PersistenceContext(unitName = "jk")
    private EntityManager entityManager;

    @Transient
    @Override
    public void save(Section section) throws Exception {
        entityManager.persist(section);

    }

    @Override
    public void edit(Section section) throws Exception {
        entityManager.merge(section);

    }

    @Override
    public void remove(Section section) throws Exception {
        entityManager.remove(section);

    }

    @Override
    public void removeById(Long id) throws Exception {

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
        return Optional.ofNullable(entityManager.find(Section.class,title));
     }
}
