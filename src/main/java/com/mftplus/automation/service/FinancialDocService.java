package com.mftplus.automation.service;

import com.mftplus.automation.model.FinancialDoc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FinancialDocService {
    void save(FinancialDoc financialDoc) throws Exception;

    void edit(FinancialDoc financialDoc) throws Exception;

    void remove(FinancialDoc financialDoc) throws Exception;

    void removeById(Long id) throws Exception;

    void removeByDocNumber(Long docNumber) throws Exception;

    Optional<FinancialDoc> findById(Long id) throws Exception;

    Optional<FinancialDoc> findByDocNumber(Long docNumber) throws Exception;

    List<FinancialDoc> findAll() throws Exception;

    List<FinancialDoc> findByDateTime(LocalDateTime dateTime) throws Exception;
}
