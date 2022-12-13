package com.libr.demo.Services;

import com.libr.demo.Controllers.WriterController;
import com.libr.demo.Entities.Book;
import com.libr.demo.Repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final java.util.logging.Logger logger = Logger.getLogger(WriterController.class.getName());
    @Override
    public Collection<Book> getBooks() {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            logger.warning("Exception: " + e.getMessage());
            return null;
        }
    }
    @Override
    public Book findByTitle(String title) {
        try {
            logger.info("Getting book by title: " + title);
            if(bookRepository.findByTitle(title)!=null){
                logger.info("Book found by title: " + title);
                return bookRepository.findByTitle(title);
            }
            else{
                logger.info("Book not found by title: " + title);
                return null;
            }
        } catch (Exception e) {
            logger.warning("Error while getting book by title: " + title + ", exception: " + e.getMessage());
            return null;
        }

    }
    @Override
    public Book addBook(String title, int releaseyr, int writerid) {
        Book book = new Book(title,releaseyr,writerid);
        logger.info("Adding book: " + book.getTitle());
        if (bookRepository.findByTitle(book.getTitle()) == null) {
            try {
                bookRepository.save(book);
                logger.info("Book added: "+book.getTitle());
            } catch (Exception e) {
                logger.warning("Error adding book: "+book.getTitle()+", Exception: "+e.getMessage());
            }
            logger.info("Book added: "+book.getTitle());
            return book;
        }
        logger.warning("Book already exists: "+book.getTitle());
        return null;
    }
    @Override
    public Boolean deleteAllByWriterid(int writerid) {
        try {
            bookRepository.findAll().stream().filter(book -> book.getWriterid() == writerid)
                    .forEach(bookRepository::delete);
            logger.info("All books by writerid deleted");
            return true;
        }
        catch (Exception e) {
            logger.warning("Error while deleting all books by writerid: "+writerid+", exception: "+e.getMessage());
            return false;
        }
    }
    @Override
    public Book findById(int id) {
        try {
            if(bookRepository.findById(id)!=null) {
                logger.info("Book found by id: "+id);
                return bookRepository.findById(id);
            }
            else {
                logger.info("Book not found by id: "+id);
                return null;
            }
        }
        catch (Exception e) {
            logger.warning("Error while getting book by id: "+id+", exception: "+e.getMessage());
            return null;
        }
    }
    @Override
    public Boolean deleteBookByTitle(String title) {
        try {
            logger.info("Deleting book by title: "+title);
            bookRepository.delete(bookRepository.findByTitle(title));
            logger.info("Book deleted by title: "+title);
            return true;
        }
        catch (Exception e) {
            logger.warning("Error while deleting book by title: "+title+", exception: "+e.getMessage());
            return false;
        }
    }

    //COOOOL
    @Override
    public Boolean deleteBookById(int id) {
        try {
            logger.info("Deleting book by id: "+id);
            bookRepository.deleteById(id);
            logger.info("Book deleted by id: "+id);
            return true;
        }
        catch (Exception e) {
            logger.warning("Error while deleting book by id: "+id+", exception: "+e.getMessage());
            return false;
        }
    }
}
