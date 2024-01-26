package com.mftplus.automation.service;

import com.mftplus.automation.model.CashDesk;

import java.util.List;
import java.util.Optional;

public interface CashDeskService {
    void save(CashDesk cashDesk) throws Exception;

    void edit(CashDesk cashDesk) throws Exception;

    void remove(CashDesk cashDesk) throws Exception;

    void removeById(Long id) throws Exception;

    void removeByCashDeskNumber(int cashDeskNumber) throws Exception;

    List<CashDesk> findAll() throws Exception;

    List<CashDesk> findByName(String name) throws Exception;

    Optional<CashDesk> findByCashDeskNumber(int cashDeskNumber) throws Exception;

    Optional<CashDesk> findByCashier(String username) throws Exception;

    Optional<CashDesk> findById(Long id) throws Exception;
}
