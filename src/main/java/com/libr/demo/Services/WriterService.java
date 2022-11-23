package com.libr.demo.Services;

import com.libr.demo.Writer;
import com.libr.demo.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api/writer")
public class WriterService{
    /*
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
    }
    @PostMapping("/add")
    public Writer saveUser(@Validated @RequestBody Writer writer) {
        return writerRepository.save(writer);
    }
    @GetMapping
    public List<Writer> findAllUsers() {
        List<Writer> l= (List<Writer>) writerRepository.findAll();
        return l;
    }
    */
}