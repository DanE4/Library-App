package com.libr.demo.Services;

import com.libr.demo.Repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {

    private final BookRepository bookRepository;
    @Autowired
    public BookServiceImplTest(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Test
    void getBooks() {

    }

    @Test
    void findByTitle() {
    }

    @Test
    void addBook() {
    }

    @Test
    void save(){
    }

    @Test
    void deleteAllByWriterid() {

    }

    @Test
    void findById() {
    }

    @Test
    void deleteBookByTitle() {
    }

    @Test
    void deleteBookById() {
    }
}