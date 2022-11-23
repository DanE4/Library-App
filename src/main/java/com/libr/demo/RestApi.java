package com.libr.demo;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/writer")
public class RestApi {
    Logger logger = org.slf4j.LoggerFactory.getLogger(RestApi.class);
    @Autowired
    private WriterRepository writerRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Writer> findWriterById(@PathVariable(value = "id") Long id) {

        Optional<Writer> writer = writerRepository.findById(id);
        if(writer.isPresent()) {
            System.out.println("Writer asked by ID");
            return ResponseEntity.ok().body(writer.get());
        } else {
            System.out.println("Writer couldn't be asked by ID");
            return ResponseEntity.notFound().build();
        }
        /*return Optional
                .ofNullable( writerRepository.findById(id).orElse(null) )
                .map( user -> ResponseEntity.ok().body(user) )          //200 OK
                .orElseGet( () -> ResponseEntity.notFound().build() );  //404 Not found
         */
    }
    @PostMapping("/add/{name}")
    //localhost:8080/api/writer/add/
    public void addWriter(@PathVariable(value = "name") String name) {
        Writer writer = new Writer();
        writer.setName(name);
        writerRepository.save(writer);
        System.out.println("Writer added");
    }

    public Writer saveUser(@Validated @RequestBody Writer writer) {
        logger.info("Writer saved");
        return writerRepository.save(writer);
    }
    @GetMapping("/all")
    public List<Writer> findAllUsers() {
        List<Writer> l= (List<Writer>) writerRepository.findAll();
        return l;
    }
}