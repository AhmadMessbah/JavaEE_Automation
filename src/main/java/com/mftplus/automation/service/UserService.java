package com.mftplus.automation.service;

import com.mftplus.automation.model.Section;
import com.mftplus.automation.model.User;
import com.mftplus.automation.model.enums.Roles;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user) throws Exception;

    void edit(User user) throws Exception;

    void remove(User user) throws Exception;

    void removeById(Long id) throws Exception;

    List<User> findAll() throws Exception;

    Optional<User> findById(Long id) throws Exception;
    Optional<User> findByUsername(String username) throws Exception;
    List<User> findByRole(Roles role) throws Exception;
    List<User> findBySection(Section section) throws Exception;
    List<User> findByActive(Boolean active) throws Exception;

}
