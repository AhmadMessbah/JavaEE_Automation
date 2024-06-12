package com.mftplus.service;

import com.mftplus.controller.exception.NoContentException;
import com.mftplus.model.CashDesk;

import java.util.List;
import java.util.Optional;

public interface CashDeskService {
    void save(CashDesk cashDesk) throws Exception;

    void edit(CashDesk cashDesk) throws NoContentException;

    void remove(CashDesk cashDesk) throws Exception;

    void removeById(Long id) throws Exception;

    Optional<CashDesk> findById(Long id) throws NoContentException;

    List<CashDesk> findAll() throws Exception;

    List<CashDesk> findByName(String name) throws Exception;

    Optional<CashDesk> findByCashDeskNumber(int cashDeskNumber) throws Exception;

    Optional<CashDesk> findByCashier(String username) throws Exception;
}
