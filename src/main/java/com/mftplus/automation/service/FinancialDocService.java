package com.mftplus.automation.service;

import com.mftplus.automation.model.FinancialDoc;
import com.mftplus.automation.model.FinancialTransaction;
import com.mftplus.automation.model.Section;
import com.mftplus.automation.model.User;
import com.mftplus.automation.model.enums.FinancialDocType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FinancialDocService {
    void save(FinancialDoc financialDoc) throws Exception;

    void edit(FinancialDoc financialDoc) throws Exception;

    void remove(FinancialDoc financialDoc) throws Exception;

    void removeById(Long id) throws Exception;

    void removeByNumberDoc(Long numberDoc) throws Exception;

    Optional<FinancialDoc> findById(Long id) throws Exception;

    Optional<FinancialDoc> findByNumberDoc(Long numberDoc) throws Exception;

    Optional<FinancialDoc> findByFinancialTransaction(Long id) throws Exception;

    List<FinancialDoc> findAll() throws Exception;

    List<FinancialDoc> findByDateTime(LocalDateTime dateTime) throws Exception;

    List<FinancialDoc> findByType(FinancialDocType type) throws Exception;

    List<FinancialDoc> findBySection(Long id) throws Exception;

    List<FinancialDoc> findBySender(String username) throws Exception;

    List<FinancialDoc>  findByReceiver(String username) throws Exception;
}
