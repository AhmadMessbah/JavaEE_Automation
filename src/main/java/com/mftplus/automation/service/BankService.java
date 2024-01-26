package com.mftplus.automation.service;

import com.mftplus.automation.model.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService {
    void  save(Bank bank) throws Exception;

    void edit(Bank bank) throws Exception;

    void remove(Bank bank) throws Exception;

    void removeById(Long id) throws Exception;

    void removeByAccountNumber(String accountNumber) throws Exception;

    List<Bank> findAll() throws Exception;

    List<Bank> findByName(String name) throws Exception;

    List<Bank> findByBranchCode(int branchCode) throws Exception;

    List<Bank> findByBranchName(String branchName) throws Exception;

    List<Bank> findByAccountType(String accountType) throws Exception;

    Optional<Bank> findById(Long id) throws Exception;

    Optional<Bank>  findByAccountNumber(String accountNumber) throws Exception;

    List<Bank> findByAccountOwner(String username) throws Exception;
}
