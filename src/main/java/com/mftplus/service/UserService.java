package com.mftplus.service;

import com.mftplus.model.Department;
import com.mftplus.model.User;
import com.mftplus.model.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user) throws Exception;
    void edit(User user) throws Exception;
    void remove(User user) throws Exception;
    //removeById = removeByUsername
    void removeByUsername(String username) throws Exception;

    List<User> findAll() throws Exception;
    //findByUsername = findById
    Optional<User> findByUsername(String username) throws Exception;
    Optional<User> findByUsernameAndPassword(String username, String password) throws Exception;
    List<User> findByRole(Role role) throws Exception;
    List<User> findByDepartment(Department department) throws Exception;
    List<User> findByActive(Boolean active) throws Exception;
    List<User> findUserByUsernames(List<String> userList) throws Exception;

    Optional<User> findById(long id) throws Exception;
}
