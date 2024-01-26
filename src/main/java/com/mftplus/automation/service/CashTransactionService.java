package com.mftplus.automation.service;

import java.util.List;
import java.util.Optional;

public interface   CashTransactionService {
    void save(CashTransaction cashTransaction) throws Exception;

    void edit(CashTransaction cashTransaction) throws Exception;

    void remove(CashTransaction cashTransaction) throws Exception;

    void removeById(Long id) throws Exception;

    List<CashTransaction> findAll() throws Exception;

    List<CashTransaction>  findByCashDeskNumber(int cashDeskNumber) throws Exception;

    Optional<CashTransaction> findById(Long id) throws Exception;
}
