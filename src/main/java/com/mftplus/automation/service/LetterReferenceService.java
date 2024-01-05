package com.mftplus.automation.service;

import com.mftplus.automation.model.LetterReference;

import java.util.List;
import java.util.Optional;

public interface LetterReferenceService {
    void save(LetterReference letterReference) throws Exception;

    void edit(LetterReference letterReference) throws Exception;

    void remove(LetterReference letterReference) throws Exception;

    void removeById(Long id) throws Exception;

    List<LetterReference> findAll() throws Exception;

    Optional<LetterReference> findById(Long id) throws Exception;
}
