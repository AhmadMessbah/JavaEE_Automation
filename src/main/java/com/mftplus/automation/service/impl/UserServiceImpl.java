package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Section;
import com.mftplus.automation.model.User;
import com.mftplus.automation.model.enums.Roles;
import com.mftplus.automation.service.UserService;
import com.mftplus.automation.service.UserService;
import jakarta.enterprise.context.SessionScoped;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Slf4j
@SessionScoped
public class UserServiceImpl implements UserService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(User user) throws Exception {

        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void edit(User user) throws Exception {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void remove(User user) throws Exception {
        entityManager.remove(user);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws Exception {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Transactional
    @Override
    public List<User> findAll() throws Exception {
        TypedQuery<User> query = entityManager.createQuery("select p from userEntity p", User.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Optional<User> findById(Long id) throws Exception {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Transactional
    @Override
    public Optional<User> findByUsername(String username) throws Exception {
        return Optional.ofNullable(entityManager.find(User.class, username));
    }
    @Transactional
    @Override
    public List<User> findByRole(Roles role) throws Exception {
        TypedQuery<User> query = entityManager.createQuery("select p from userEntity p", User.class);
        return query.getResultList();
    }
    @Transactional
    @Override
    public List<User> findBySection(Section section) throws Exception {
        TypedQuery<User> query = entityManager.createQuery("select p from userEntity p", User.class);
        return query.getResultList();
    }
    @Transactional
    @Override
    public List<User> findByActive(Boolean section) throws Exception {
        TypedQuery<User> query = entityManager.createQuery("select p from userEntity p", User.class);
        return query.getResultList();
    }


}
