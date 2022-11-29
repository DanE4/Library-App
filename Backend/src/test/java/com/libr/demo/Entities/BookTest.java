package com.libr.demo.Entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void validateBook() {
        Writer writer = new Writer("name", LocalDate.of(2000, 1, 1));
        Book book = new Book("title", 2020, writer);
        assertEquals("title", book.getTitle());
        assertEquals(2020, book.getReleaseyr());
        assertEquals(writer, book.getWriter());
    }

    @Test
    void validateId() {
        Book book = new Book("title", 2020, new Writer("name", LocalDate.of(2000, 1, 1)));
        book.setId(1);
        assertEquals(1, book.getId());
    }

    @Test
    void validateTitle() {
        Book book = new Book("title", 2020, new Writer("name", LocalDate.of(2000, 1, 1)));
        book.setTitle("new title");
        assertEquals("new title", book.getTitle());
    }

    @Test
    void validateReleaseyr() {
        Book book = new Book("title", 2020, new Writer("name", LocalDate.of(2000, 1, 1)));
        book.setReleaseyr(2021);
        assertEquals(2021, book.getReleaseyr());
    }

    @Test
    void validateWriter() {
        Writer writer = new Writer("name", LocalDate.of(2000, 1, 1));
        Book book = new Book("title", 2020, writer);
        Writer newWriter = new Writer("new writer", LocalDate.of(2001, 1, 1));
        book.setWriter(newWriter);
        assertEquals(newWriter, book.getWriter());
    }
}