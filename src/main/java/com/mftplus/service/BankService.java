package com.mftplus.service;

import com.mftplus.controller.exception.NoContentException;
import com.mftplus.model.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService {
    void save(Bank bank) throws Exception;

    void edit(Bank bank) throws NoContentException;

    void remove(Bank bank) throws Exception;

    void removeById(Long id) throws Exception;

    Optional<Bank> findById(Long id) throws NoContentException;

    List<Bank> findAll() throws Exception;

    List<Bank> findByName(String name) throws Exception;

    List<Bank> findByBranchCode(Long branchCode) throws Exception;

    List<Bank> findByBranchName(String branchName) throws Exception;

    Optional<Bank> findByAccountNumber(String accountNumber) throws Exception;
}