package com.mftplus.automation.service;

import com.mftplus.automation.model.Letter;
import com.mftplus.automation.model.Reference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReferenceService {
    void save(Reference reference) throws Exception;
    void edit(Reference reference) throws Exception;
    void remove(Reference reference) throws Exception;
    void removeById(Long id) throws Exception;

    Optional<Reference> findById(Long id) throws Exception;
    List<Reference> findAll() throws Exception;
    Optional<Reference> findByReferenceSenderOrReceiver(Long id) throws Exception;
    List<Reference> findByRefDate(LocalDateTime date) throws Exception;
    Optional<Reference> findByLetterId(Letter letterId) throws Exception;
    List<Reference> findByValidate(Boolean validate) throws Exception;
    List<Reference> findByParaph(String paraph) throws Exception;
    List<Reference> findByPriority(List<Reference> priority) throws Exception;
    List<Reference> findByStatus(Boolean status) throws Exception;
}
