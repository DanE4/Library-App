package com.libr.demo.Controllers;

import com.libr.demo.Entities.Response;
import com.libr.demo.Exception.ApiRequestException;
import com.libr.demo.Services.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@CrossOrigin(origins="*", allowedHeaders="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BookController {
    private final BookServiceImpl bookServiceImpl;
    //http://localhost:8888/api/book/list
    @GetMapping("/list")
    public ResponseEntity<Response> getBooks() {
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Books",bookServiceImpl.getBooks()))
                    .message("Books found")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while getting books, exception: " + e.getMessage());
        }
    }

    //http://localhost:8888/api/book/find/A Game of Thrones
    @GetMapping("/find/{title}")
    public ResponseEntity<Response>  findByTitle(@PathVariable String title) {
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Book",bookServiceImpl.findByTitle(title)))
                    .message("Book found")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while getting book by title: " + title + ", exception: " + e.getMessage());
        }
    }

    //http://localhost:8888/api/book/findid/1
    @GetMapping("/findid/{id}")
    public ResponseEntity<Response>findById(@PathVariable int id) {
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Book",bookServiceImpl.findById(id)))
                    .message("Book found")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while getting book by id: " + id + ", exception: " + e.getMessage());
        }
    }
    //COOL
    @PostMapping("/add/{title}/{releaseyr}/{writerid}/{id}")
    public ResponseEntity<Response> add(@PathVariable String title, @PathVariable int releaseyr, @PathVariable int writerid, @PathVariable int id) {
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Book",bookServiceImpl.addBook(title,releaseyr,writerid,id)))
                    .message("Book added/updated")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while adding/updating book: " + title + ", exception: " + e.getMessage());
        }
    }
    //COOOL
    @DeleteMapping("/delete/{title}")
    public ResponseEntity<Response> deleteBook(@PathVariable String title) {
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Book",bookServiceImpl.deleteBookByTitle(title)))
                    .message("Book deleted")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while deleting book: " + title + ", exception: " + e.getMessage());
        }
    }
    //COOOL
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Response> deleteBookById(@PathVariable int id) {
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Book",bookServiceImpl.deleteBookById(id)))
                    .message("Book deleted")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while deleting book by id: " + id + ", exception: " + e.getMessage());
        }
    }
    //COOL
    @DeleteMapping("/deleteAll/{writerid}")
    public ResponseEntity<Response> deleteAllByWriterId(@PathVariable int writerid) {
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Book",bookServiceImpl.deleteAllByWriterId(writerid)))
                    .message("Books deleted")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while deleting books by writer id: " + writerid + ", exception: " + e.getMessage());
        }
    }
}