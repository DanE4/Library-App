package com.libr.demo.Entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WriterTest {

    @Test
    void validateWriter() {
        Writer writer = new Writer("name", LocalDate.of(2020, 1, 1));
        assertEquals("name", writer.getName());
        assertEquals(LocalDate.of(2020, 1, 1), writer.getLocDat());

    }

    @Test
    void validateId() {
        Writer writer = new Writer("name", LocalDate.of(2020, 1, 1));
        writer.setId(1);
        assertEquals(1, writer.getId());
    }

    @Test
    void validateName() {
        Writer writer = new Writer("name", LocalDate.of(2020, 1, 1));
        writer.setName("new name");
        assertEquals("new name", writer.getName());
    }

    @Test
    void validateBirth() {
        Writer writer = new Writer("name", LocalDate.of(2020, 1, 1));
        writer.setBirth(java.sql.Date.valueOf(LocalDate.of(2021, 1, 1)));
        assertEquals(LocalDate.of(2021, 1, 1), writer.getLocDat());
    }
}