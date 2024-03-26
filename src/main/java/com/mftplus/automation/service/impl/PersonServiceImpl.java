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
//        if (findByUsername(person.getUser().getUsername().toString()).isPresent()){
//            throw new PersonalInfoAlreadyExists();
//        }
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
        person = entityManager.find(Person.class, person.getId());
        person.setDeleted(true);
        entityManager.merge(person);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        Person person = entityManager.find(Person.class, id);
        person.setDeleted(true);
        entityManager.merge(person);
    }

    @Transactional
    @Override
    public List<Person> findAll() throws Exception {
        TypedQuery<Person> query = entityManager.createQuery("select p from personEntity p where p.deleted=false", Person.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<Person> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(Person.class, id));
    }

    @Transactional
    @Override
    public Optional<Person> findByUsername(String username) throws Exception {
        TypedQuery<Person> query = entityManager.createQuery("select p from personEntity p where p.user=:username", Person.class);
        query.setParameter("username", username);
        return Optional.ofNullable(entityManager.find(Person.class, username));
    }

    @Transactional
    @Override
    public List<Person> findByName(String name) throws Exception {
        TypedQuery<Person> query = entityManager.createQuery("select p from personEntity p where p.name=:name", Person.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Person> findByFamily(String family) throws Exception {
        TypedQuery<Person> query = entityManager.createQuery("select p from personEntity p where p.family=:family", Person.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Person> findByNameAndFamily(String name, String family) throws Exception {
        TypedQuery<Person> query = entityManager.createQuery("select oo from personEntity oo where oo.name=:name and oo.family=:family", Person.class);
        query.setParameter(name,"name");
        query.setParameter(family,"family");
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<Person> findByNationalCode(String code) throws Exception {
        return Optional.ofNullable(entityManager.find(Person.class, code));
    }

}
