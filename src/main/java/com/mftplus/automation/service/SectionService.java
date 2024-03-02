package com.mftplus.automation.service;

import com.mftplus.automation.model.Section;

import java.util.List;
import java.util.Optional;

public interface SectionService {
    void save(Section section) throws Exception;
    void edit(Section section) throws Exception;
    void remove(Section section) throws Exception;
    void removeById(Long id) throws Exception;

    List<Section> findAll() throws Exception;
    Optional<Section> findById(Long id) throws Exception;
    Optional<Section> findByTitle(String title) throws Exception;
}
