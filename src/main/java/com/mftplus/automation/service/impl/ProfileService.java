package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
    void save(Profile profile) throws Exception;

    void edit(Profile profile) throws Exception;

    void remove(Profile profile) throws Exception;

    void removeById(Long id) throws Exception;

    List<Profile> findAll() throws Exception;

    Optional<Profile> findById(Long id) throws Exception;
}
