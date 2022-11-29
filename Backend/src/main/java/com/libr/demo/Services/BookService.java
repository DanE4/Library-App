package com.libr.demo.Services;

import com.libr.demo.Entities.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookService {
    Collection<Book> getBooks();

    Book findByTitle(String title);

    void updateBook(Book b, int idi);

    Book addBook(String title, int releaseyr, int writerid, int id);

    Boolean deleteAllByWriterId(int id);

    Book findById(int id);

    Boolean deleteBookByTitle(String title);

    Boolean deleteBookById(int id);

}
