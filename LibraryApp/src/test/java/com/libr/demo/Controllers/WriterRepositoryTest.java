package com.libr.demo.Controllers;

import com.libr.demo.Entities.Writer;
import com.libr.demo.Repositories.WriterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

class WriterRepositoryTest {

    @Test
    void welcome() {
    }

    @Autowired
    private WriterRepository writerRepository;

    @Test
    void itShouldCheckIfWriterExistsByName() {
        Writer writer = new Writer("John", LocalDate.of(1990, 12, 12));
        writerRepository.save(writer);
        boolean exp=writerRepository.findByName(writer.getName())!=null;
        Assertions.assertTrue(exp);
    }

    @Test
    void itShouldCheckIfWriterExistsById() {
        Writer writer = new Writer("John", LocalDate.of(1990, 12, 12));
        writerRepository.save(writer);
        boolean exp=writerRepository.existsById(writer.getId());
        Assertions.assertTrue(exp);
    }

    @Test
    void findById() {
    }

    @Test
    void addWriter() {
    }

    @Test
    void deleteWriter() {
    }

    @Test
    void saveDepartment() {
    }

    @Test
    void fetchWriters() {
    }

    @Test
    void updateWriter() {
    }

    @Test
    void deleteWriterById() {
    }
}