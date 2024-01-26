package com.mftplus.automation.service;

import com.mftplus.automation.model.FinancialTransaction;
import com.mftplus.automation.model.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FinancialTransactionServiceImpl {
    void save(FinancialTransaction financialTransaction) throws Exception;

    void edit(FinancialTransaction financialTransaction) throws Exception;

    void remove(FinancialTransaction financialTransaction) throws Exception;

    void removeById(Long id) throws Exception;

    void removeByTrackingCode (int trackingCode) throws Exception;

    List<FinancialTransaction> findAll() throws Exception;

    Optional<FinancialTransaction> findById(Long id) throws Exception;

    Optional<FinancialTransaction> findByTrackingCode(int trackingCode) throws Exception;

    List<FinancialTransaction> findByBankInvolved(Long id) throws Exception;

    List<FinancialTransaction> findByDateTime(LocalDateTime dateTime) throws Exception;

    List<FinancialTransaction> findByType(TransactionType transactionType) throws Exception;

    List<FinancialTransaction> findByUserId(Long id) throws Exception;

    Optional<FinancialTransaction> findByFinancialDoc(Long id) throws Exception;
}
