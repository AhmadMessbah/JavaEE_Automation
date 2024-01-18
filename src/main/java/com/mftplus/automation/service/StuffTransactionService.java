package com.mftplus.automation.service;

import com.mftplus.automation.model.StuffTransaction;

import java.util.List;
import java.util.Optional;

public interface StuffTransactionService {
    void save(StuffTransaction stuffTransaction) throws Exception;

    void edit(StuffTransaction stuffTransaction) throws Exception;

    void remove(StuffTransaction stuffTransaction) throws Exception;

    void removeById(Long id) throws Exception;

    List<StuffTransaction> findAll() throws Exception;

    Optional<StuffTransaction> findById(Long id) throws Exception;
}
