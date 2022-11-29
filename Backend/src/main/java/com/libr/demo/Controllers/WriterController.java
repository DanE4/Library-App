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
@CrossOrigin(origins="*", allowedHeaders="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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
    @CrossOrigin(origins="*", allowedHeaders="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<Response> findByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Writer",writerServiceImpl.findByName(name)))
                    .message("Writers found")
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
                    .data(Map.of("Writer",writerServiceImpl.findById(id)))
                    .message("Writer found")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while getting book by id: " + id + ", exception: " + e.getMessage());
        }
    }
    @GetMapping("/list")
    @CrossOrigin(origins="*", allowedHeaders="*", methods={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<Response> list() {
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Writers",writerServiceImpl.getWriters()))
                    .message("Writer found")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while getting books, exception: " + e.getMessage());
        }
    }
    //http://localhost:8888/api/writer/add/Dan Brown/1964-06-22
    @PostMapping("/add/{name}/{birth}/{id}")
    public ResponseEntity<Response> add(@PathVariable String name, @PathVariable String birth,@PathVariable int id) {
        if(name == null || birth == null) {
            logger.warn("Writer not added, name or birth is null");
            return ResponseEntity.notFound().build();
        }
        try {
            return ResponseEntity.ok((Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("Writer",writerServiceImpl.addWriter(name, birth, id)))
                    .message("Writer added/updated")
                    .status(OK).statusCode(OK.value()).build()));
        } catch (Exception e) {
            throw new ApiRequestException("Error while adding/updating writer, exception: " + e.getMessage());
        }
    }
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