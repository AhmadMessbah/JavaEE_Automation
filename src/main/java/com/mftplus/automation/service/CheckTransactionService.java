package com.mftplus.automation.service;

import com.mftplus.automation.model.CheckPayment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CheckTransactionService {
    void save(CheckPayment checkPayment) throws Exception;

    void edit(CheckPayment checkPayment) throws Exception;

    void remove(CheckPayment checkPayment) throws Exception;

    void removeById(Long id) throws Exception;

    void removeByCheckNumber(String checkNumber) throws Exception;

    List<CheckPayment> findAll() throws Exception;

    List<CheckPayment> findByCheckDueDate(LocalDateTime checkDueDate)throws Exception;

    Optional<CheckPayment> findByCashDeskNumber(int cashDeskNumber) throws Exception;

    Optional<CheckPayment> findByCheckNumber(String checkNumber) throws Exception;

    Optional<CheckPayment> findById(Long id) throws Exception;
}
