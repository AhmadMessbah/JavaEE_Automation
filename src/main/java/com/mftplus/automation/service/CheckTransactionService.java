package com.mftplus.automation.service;

import com.mftplus.automation.model.CheckTransaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CheckTransactionService {
    void save(CheckTransaction checkTransaction) throws Exception;

    void edit(CheckTransaction checkTransaction) throws Exception;

    void remove(CheckTransaction checkTransaction) throws Exception;

    void removeById(Long id) throws Exception;

    void removeByCheckNumber(String checkNumber) throws Exception;

    List<CheckTransaction> findAll() throws Exception;

    List<CheckTransaction> findByCheckDueDate(LocalDateTime checkDueDate)throws Exception;

    Optional<CheckTransaction> findByCashDeskNumber(int cashDeskNumber) throws Exception;

    Optional<CheckTransaction> findByCheckNumber(String checkNumber) throws Exception;

    Optional<CheckTransaction> findById(Long id) throws Exception;
}
