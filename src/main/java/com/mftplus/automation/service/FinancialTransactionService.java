package com.mftplus.automation.service;

import com.mftplus.automation.model.FinancialTransaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface  FinancialTransactionService {
    void save(FinancialTransaction financialTransaction) throws Exception;

    void edit(FinancialTransaction financialTransaction) throws Exception;

    void remove(FinancialTransaction financialTransaction) throws Exception;

    void removeById(Long id) throws Exception;

    void removeByTrackingCode (int trackingCode) throws Exception;

    List<FinancialTransaction> findAll() throws Exception;

    Optional<FinancialTransaction> findById(Long id) throws Exception;

    Optional<FinancialTransaction> findByTrackingCode(int trackingCode) throws Exception;

    List<FinancialTransaction> findByDescription(String description) throws Exception;

    List<FinancialTransaction> findByDateTime(LocalDateTime dateTime) throws Exception;

    List<FinancialTransaction> findByPayer(String username) throws Exception;

    List<FinancialTransaction> findBySection(String title) throws Exception;

    Optional<FinancialTransaction> findByFinancialDoc(Long docNumber) throws Exception;
}
