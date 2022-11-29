package com.libr.demo.Services;

import com.libr.demo.Entities.Book;
import com.libr.demo.Entities.Writer;
import com.libr.demo.Repositories.BookRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@Component
class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository bookRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(new Book("title", 2020, new Writer("name", LocalDate.of(2000, 1, 1)))));
        List<Book> books = (List<Book>) bookService.getBooks();
        assertEquals(1, books.size());
    }
    @Test
    void willThrowExceptionWhenCantGetBooks() {
        when(bookRepository.findAll()).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> bookService.getBooks());
    }

    @Test
    void findByTitle() {
        Book book = new Book("title", 2020, new Writer("name", LocalDate.of(2000, 1, 1)));
        when(bookRepository.findByTitle("title")).thenReturn(book);
        assertEquals(book, bookService.findByTitle("title"));
    }
    @Test
    void willThrowExceptionWhenCantGetBookByTitle() {
        when(bookRepository.findByTitle("title")).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> bookService.findByTitle("title"));
    }
    @Test
    void willReturnNullWhenCantGetBookByTitle() {
        when(bookRepository.findByTitle("title")).thenReturn(null);
        assertNull(bookService.findByTitle("title"));
    }
    @Test
    void deleteAllByWriterId() {
        int id= 1;
        Book book = new Book("The Lord of the Rings", 2020,new Writer("J.R.R. Tolkien", LocalDate.of(1892, 1, 3)));
        when(bookRepository.findAll().stream().filter(b -> b.getWriter().getId() == id)).thenReturn(Stream.of(book).filter(b -> b.getWriter().getId() == id));
        bookService.deleteAllByWriterId(id);
        Mockito.verify(bookRepository).delete(book);
    }
    @Test
    void willReturnTrueWhenBookIsDeleted(){
        Writer writer = new Writer("J.R.R. Tolkien", LocalDate.of(1892, 1, 3));
        Book book = new Book("The Lord of the Rings", 2020, writer);
        bookRepository.save(book);
        bookRepository.delete(book);
        assertNull(bookRepository.findByTitle("The Lord of the Rings"));
    }
    @Test
    void willReturnFalseWhenBookIsNotDeleted(){
        Writer writer = new Writer("J.R.R. Tolkien", LocalDate.of(1892, 1, 3));
        Book book = new Book("The Lord of the Rings", 2020, writer);
        bookRepository.save(book);
        bookRepository.delete(book);
        assertNotNull(bookRepository.findByTitle("The Lord of the Rings"));
    }

    @Test
    void findById() {
        Book book = new Book("title", 2020, new Writer("name", LocalDate.of(2000, 1, 1)));
        when(bookRepository.findById(1)).thenReturn(book);
        assertEquals(book, bookService.findById(1));
    }
    @Test
    void willReturnNullWhenCantGetBookById() {
        when(bookRepository.findById(1)).thenReturn(null);
        assertNull(bookService.findById(1));
    }
    @Test
    void willThrowExceptionWhenCantGetBookById() {
        when(bookRepository.findById(1)).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> bookService.findById(1));
    }



    @Test
    void deleteBookByTitle() {
        Book book = new Book("title", 2020, new Writer("name", LocalDate.of(2000, 1, 1)));
        bookRepository.save(book);
        bookService.deleteBookByTitle("title");
        assertNull(bookRepository.findByTitle("title"));
    }
    @Test
    void willThrowExceptionWhenCantDeleteBookByTitle() {
        when(bookRepository.findByTitle("title")).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> bookService.deleteBookByTitle("title"));
    }
    @Test
    void updateBook() {

    }
    @Test
    void addBook() {
        bookService.addBook("title", 2000, 1,1);
        ArgumentCaptor<Book> argumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(argumentCaptor.capture());
        assertEquals("title", argumentCaptor.getValue().getTitle());
        assertEquals(2000, argumentCaptor.getValue().getReleaseyr());
        assertEquals(1, argumentCaptor.getValue().getWriter().getId());

    }
    @Test
    void deleteBookById() {
        Book book = new Book("title", 2020, new Writer("name", LocalDate.of(2000, 1, 1)));
        bookRepository.save(book);
        bookService.deleteBookById(1);
        assertNull(bookRepository.findByTitle("title"));
    }
    @Test
    void willThrowExceptionAndReturnFalseWhenCantDeleteBookById() {
        when(bookRepository.findById(1)).thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () -> bookService.deleteBookById(1));
    }
}