package com.libr.demo.Services;

import com.libr.demo.Entities.Book;
import com.libr.demo.Entities.Writer;
import com.libr.demo.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
    public Optional<Book> findByTitle(String name) {
        return Optional.ofNullable(bookRepository.findByTitle(name));
    }
    public Optional<Book> addBook(Book book) {
        if (bookRepository.findByTitle(book.getTitle()) == null) {
            System.out.println("BookService.addBook");
            try {
                //SET foreign_key_checks = 0;

                bookRepository.save(book);
            } catch (Exception e) {
                System.out.println("BookService.addBook: " + e);
            }
            return Optional.of(book);
        }
        return Optional.empty();
    }

    public void deleteAllByWriterid(int writerid) {
        System.out.println("DELETINGGG");
        bookRepository.findAll().forEach(book -> {
            if(book.getWriterid() == writerid) {
                System.out.println(book.getTitle());
            }
        });
        bookRepository.findAll().stream().filter(book -> book.getWriterid() == writerid)
                .forEach(bookRepository::delete);
    }

    public Optional<Book> findById(int id) {
        return Optional.ofNullable(bookRepository.findById(id));
    }

    public void deleteBookByTitle(String title) {
        bookRepository.findAll().stream().filter(book -> book.getTitle().equals(title))
                .forEach(bookRepository::delete);
    }

    //COOOOL
    public void deleteBookById(int id) {
        bookRepository.findAll().stream().filter(book -> book.getId() == id)
                .forEach(bookRepository::delete);
    }
}
