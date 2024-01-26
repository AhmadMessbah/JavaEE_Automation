package com.mftplus.automation.service;

import com.mftplus.automation.model.BankDepositTransaction;

import java.util.List;
import java.util.Optional;

public interface BankDepositTransactionService {
    void save(BankDepositTransaction bankDepositTransaction) throws Exception;

    void edit(BankDepositTransaction bankDepositTransaction) throws Exception;

    void remove(BankDepositTransaction bankDepositTransaction) throws Exception;

    void removeById(Long id) throws Exception;

    List<BankDepositTransaction> findAll() throws Exception;

    List<BankDepositTransaction> findByDepositCode(String depositCode) throws Exception;

    List<BankDepositTransaction> findByBankInvolved(String accountNumber) throws Exception;

    Optional<BankDepositTransaction> findById(Long id) throws Exception;
}
