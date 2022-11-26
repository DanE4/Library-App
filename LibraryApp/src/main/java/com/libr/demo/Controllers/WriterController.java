package com.libr.demo.Controllers;

import com.libr.demo.Entities.Response;
import com.libr.demo.Exception.ApiRequestException;
import com.libr.demo.Services.WriterServiceImpl;
import com.libr.demo.Entities.Writer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/writer")
@RequiredArgsConstructor
public class WriterController {

    private static final Logger logger = LoggerFactory.getLogger(WriterController.class);
    private final WriterServiceImpl writerServiceImpl;

    //testing jsp
    @RequestMapping("welcome")
    public String welcome(){
        System.out.println("Welcome");
        return "welcome";
    }
    //http://localhost:8888/api/writer/find/Dan Brown
    @GetMapping("/find/{name}")
    public ResponseEntity<Response> findByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Book",writerServiceImpl.findByName(name)))
                    .message("Books found")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while getting books, exception: " + e.getMessage());
        }
    }
    //http://localhost:8888/api/writer/findid/2
    @GetMapping("/findid/{id}")
    public ResponseEntity<Response> findById(@PathVariable int id) {
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Book",writerServiceImpl.findById(id)))
                    .message("Book found")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while getting book by id: " + id + ", exception: " + e.getMessage());
        }
    }
    @GetMapping("/writers")
    public ResponseEntity<Response> list() {
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Books",writerServiceImpl.getWriters()))
                    .message("Books found")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while getting books, exception: " + e.getMessage());
        }
    }
    //http://localhost:8888/api/writer/add/Dan Brown/1964-06-22
    @PostMapping("/add/{name}/{birth}")
    public ResponseEntity<Response> add(@PathVariable String name, @PathVariable String birth) {
        if(name == null || birth == null) {
            logger.warn("Writer not added, name or birth is null");
            return ResponseEntity.notFound().build();
        }
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Writer",writerServiceImpl.addWriter(name, birth)))
                    .message("Writer added")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while adding writer, exception: " + e.getMessage());
        }
    }
    //COOOOOL
    //http://localhost:8888/api/writer/update/Dan Brown/1964-06-22
    @PutMapping("/update/{id}/{birth}")
    public ResponseEntity<Response> updateWriterBirth(@PathVariable("id") int id,@PathVariable("birth") String birth) {
        if(birth == null) {
            logger.warn("Writer not updated, birth is null");
            return ResponseEntity.badRequest().build();
        }
        else if(id == 0) {
            logger.warn("Writer not updated, id is null");
            return ResponseEntity.badRequest().build();
        }
        else {
            try {
                return ResponseEntity.ok((Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("Writer",writerServiceImpl.updateWriterBirth(id, LocalDate.parse(birth))))
                        .message("Writer updated")
                        .status(OK).statusCode(OK.value()).build()));
            } catch (Exception e) {
                throw new ApiRequestException("Error while updating writer, exception: " + e.getMessage());
            }
        }
    }
    //nooooooooooooOOOOOOOOOOOOOOOOOOOOOOOO not good because of
    //http://localhost:8888/api/writer/delete/Dan Brown
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Response> deleteWriterByName(@PathVariable String name) {
        if(name == null) {
            logger.warn("Writer not deleted, name is null");
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Writer",writerServiceImpl.deleteWriterByName(name)))
                    .message("Writer deleted")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while deleting writer, exception: " + e.getMessage());
        }
    }
    /*
    @PostMapping("/writer")
    public Writer saveWriter(@RequestBody Writer writer) {
        System.out.println("/writer");
        return writerService.saveWriter(writer);
    }
    */
    //http://localhost:8888/api/writer/delete/2
    @DeleteMapping("/deleteid/{id}")
    public ResponseEntity<Response> deleteWriterById(@PathVariable("id") int id) {
        if(id == 0) {
            logger.warn("Writer not deleted, id is null");
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Writer",writerServiceImpl.deleteWriterById(id)))
                    .message("Writer deleted")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while deleting writer, exception: " + e.getMessage());
        }
    }
}