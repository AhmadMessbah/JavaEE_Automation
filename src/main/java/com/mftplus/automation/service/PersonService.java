package com.mftplus.automation.service;

import com.mftplus.automation.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    void save(Person person) throws Exception;
    void edit(Person person) throws Exception;
    void remove(Person person) throws Exception;
    void removeById(Long id) throws Exception;

    List<Person> findAll() throws Exception;
    Optional<Person> findById(Long id) throws Exception;
    List<Person> findByName(String name) throws Exception;
    List<Person> findByFamily(String family) throws Exception;
    Optional<Person> findByNationalCode(String code) throws Exception;
}
