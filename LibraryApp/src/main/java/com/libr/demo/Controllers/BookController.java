package com.libr.demo.Controllers;

import com.libr.demo.Entities.Book;
import com.libr.demo.Entities.Writer;
import com.libr.demo.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/book")
public class BookController {

    //spring bean
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    //http://localhost:8888/api/book/list
    @GetMapping("/list")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    //http://localhost:8888/api/book/find/A Game of Thrones
    @GetMapping("/find/{title}")
    public Optional<Book> findByTitle(@PathVariable String title) {
        return bookService.findByTitle(title);
    }

    //http://localhost:8888/api/book/findid/1
    @GetMapping("/findid/{id}")
    public Optional<Book> findById(@PathVariable int id) {
        return bookService.findById(id);
    }
    //COOL
    @PostMapping("/add/{title}/{releaseyr}/{writerid}")
    public Optional<Book> add(@PathVariable String title, @PathVariable int releaseyr, @PathVariable int writerid) {
        return bookService.addBook(new Book(title, releaseyr, writerid));
    }
    //COOOL
    @DeleteMapping("/delete/{title}")
    public void deleteBook(@PathVariable String title) {
        bookService.deleteBookByTitle(title);
    }
    //COOOL
    @DeleteMapping("/deletebyid/{id}")
    public void deleteBookById(@PathVariable int id) {
        bookService.deleteBookById(id);
    }
    //COOL
    @DeleteMapping("/deleteAll/{writerid}")
    public void deleteAllByWriterId(@PathVariable int writerid) {
        bookService.deleteAllByWriterid(writerid);
    }


}