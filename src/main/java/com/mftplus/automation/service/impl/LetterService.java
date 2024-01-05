package com.mftplus.automation.service.impl;

import com.mftplus.automation.model.Letter;

import java.util.List;
import java.util.Optional;

public interface LetterService {
    void save(Letter letter) throws Exception;

    void edit(Letter letter) throws Exception;

    void remove(Letter letter) throws Exception;

    void removeById(Long id) throws Exception;

    List<Letter> findAll() throws Exception;

    Optional<Letter> findById(Long id) throws Exception;
}
