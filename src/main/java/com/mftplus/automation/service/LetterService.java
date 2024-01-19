package com.mftplus.automation.service;

import com.mftplus.automation.model.Letter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LetterService {
    void save(Letter letter) throws Exception;
    void edit(Letter letter) throws Exception;
    void remove(Letter letter) throws Exception;
    void removeById(Long id) throws Exception;

    Optional<Letter> findById(Long id) throws Exception;
    List<Letter> findAll() throws Exception;
    List<Letter> findByTitle(String title) throws Exception;
    List<Letter> findByContext(String context) throws Exception;
    List<Letter> findByDate(LocalDate date) throws Exception;
    Optional<Letter> findByRegisterNumber(Long registerNumber) throws Exception;
    List<Letter> findByRegisterDate(LocalDateTime dateTime) throws Exception;
    List<Letter> findBySenderNameAndTitle(String senderName,String senderTitle) throws Exception;
    List<Letter> findBySectionId(Long sectionId) throws Exception;
}
