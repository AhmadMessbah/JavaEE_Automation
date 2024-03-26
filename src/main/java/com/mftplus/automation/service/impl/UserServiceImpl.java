package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Section;
import com.mftplus.automation.model.User;
import com.mftplus.automation.model.enums.Role;
import com.mftplus.automation.service.UserService;
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
public class UserServiceImpl implements UserService, Serializable {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(User user) throws Exception {
        log.info("User Saved");
        user.setActive(true);
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
        user = entityManager.find(User.class, user.getUsername());
        user.setDeleted(true);
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void removeByUsername(String username) throws Exception {
        User user = entityManager.find(User.class, username);
        user.setDeleted(true);
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public List<User> findAll() throws Exception {
        TypedQuery<User> query = entityManager.createQuery("select u from userEntity u where u.deleted=false", User.class);
        return query.getResultList();
    }

//    @Transactional
//    @Override
//    public Optional<User> findByUsername(String username) throws Exception {
//        return Optional.ofNullable(entityManager.find(User.class, username));
//    }

    @Transactional
    @Override
    public Optional<User> findByUsername(String username) throws Exception {
        TypedQuery<User> query = entityManager.createQuery("select u from userEntity u where u.username=:username", User.class);
        query.setParameter("username", username);
        return Optional.ofNullable(entityManager.find(User.class, username));
    }

    //todo
    @Transactional
    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) throws Exception {
        TypedQuery<User> query =  entityManager.createQuery("select u from userEntity u where u.username=:username and u.password=:password",User.class);
        query.setParameter("username", username);
        query.setParameter("password",password);
        List<User> result = query.getResultList();
        return Optional.ofNullable((result.isEmpty()) ? null : result.get(0));
    }

    @Transactional
    @Override
    public List<User> findByRole(Role role) throws Exception {
        TypedQuery<User> query = entityManager.createQuery("select u from userEntity u", User.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<User> findBySection(Section section) throws Exception {
        TypedQuery<User> query = entityManager.createQuery("select u from userEntity u", User.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<User> findByActive(Boolean section) throws Exception {
        TypedQuery<User> query = entityManager.createQuery("select u from userEntity u", User.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<User> findUserByIds(List<String> userList) throws Exception {
        TypedQuery<User> query = entityManager.createQuery("select oo from userEntity oo where oo.username in (:userList)", User.class);
        query.setParameter(userList.toString(),"userList");
        return query.getResultList();
    }
}
