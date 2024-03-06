package com.mftplus.automation.service;


import com.mftplus.automation.model.Attach;

import java.util.List;
import java.util.Optional;

public interface AttachService {
    void save(Attach attach) throws Exception;
    void edit(Attach attach) throws Exception;
    void remove(Attach attach) throws Exception;
    void removeById(Long id) throws Exception;

    Optional<Attach> findById(Long id) throws Exception;
    List<Attach> findAll() throws Exception;
    List<Attach> findByTitle(String title) throws Exception;
}
