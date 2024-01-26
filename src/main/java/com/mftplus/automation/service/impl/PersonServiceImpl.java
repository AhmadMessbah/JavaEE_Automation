package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Person;
import com.mftplus.automation.service.PersonService;
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
public class PersonServiceImpl implements PersonService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Person person) throws Exception {
        entityManager.persist(person);
    }

    @Transactional
    @Override
    public void edit(Person person) throws Exception {
        entityManager.merge(person);
    }

    @Transactional
    @Override
    public void remove(Person person) throws Exception {
        entityManager.remove(person);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        Person person = entityManager.find(Person.class, id);
        entityManager.remove(person);
    }

    @Transactional
    @Override
    public List<Person> findAll() throws Exception {
        TypedQuery<Person> query = entityManager.createQuery("select p from personEntity p", Person.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<Person> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(Person.class, id));
    }
    @Transactional
    @Override
    public List<Person> findByName(String name) throws Exception {
        TypedQuery<Person> query = entityManager.createQuery("select p from personEntity p", Person.class);
        return query.getResultList();
    }
    @Transactional
    @Override
    public List<Person> findByFamily(String family) throws Exception {
        TypedQuery<Person> query = entityManager.createQuery("select p from personEntity p", Person.class);
        return query.getResultList();
    }
    @Transactional
    @Override
    public Optional<Person> findByNationalCode(String code) throws Exception {
        return Optional.ofNullable(entityManager.find(Person.class, code));
    }

}
