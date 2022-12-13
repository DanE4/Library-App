package com.libr.demo.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void validateBook() {
        Book book = new Book("title", 2020, 1);
        assertEquals("title", book.getTitle());
        assertEquals(2020, book.getReleaseyr());
        assertEquals(1, book.getWriterid());
    }

    @Test
    void validateId() {
        Book book = new Book("title", 2020, 1);
        book.setId(1);
        assertEquals(1, book.getId());
    }

    @Test
    void validateTitle() {
        Book book = new Book("title", 2020, 1);
        book.setTitle("new title");
        assertEquals("new title", book.getTitle());
    }

    @Test
    void validateReleaseyr() {
        Book book = new Book("title", 2020, 1);
        book.setReleaseyr(2021);
        assertEquals(2021, book.getReleaseyr());
    }

    @Test
    void validateWriterid() {
        Book book = new Book("title", 2020, 1);
        book.setWriterid(2);
        assertEquals(2, book.getWriterid());
    }
}