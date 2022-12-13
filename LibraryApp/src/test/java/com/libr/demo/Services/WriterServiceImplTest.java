package com.libr.demo.Services;

import com.libr.demo.Entities.Writer;
import com.libr.demo.Repositories.WriterRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WriterServiceImplTest {
    WriterRepository writerRepository;

    @Test
    void updateWriterName() {



    }

    @Test
    void deleteById() {
    }

    @Test
    void getWriters() {
    }

    @Test
    void findByName() {
        //given
        Writer writer = new Writer("Kovács János", LocalDate.of(1990, 12, 12));
        writerRepository.save(writer);

        //when
        writerRepository.findByName("Kovács János");
        //then

    }

    @Test
    void addWriter() {
    }

    @Test
    void findById() {
    }

    @Test
    void updateWriter() {
    }

    @Test
    void deleteWriterById() {
    }

    @Test
    void deleteWriterByName() {
    }
}