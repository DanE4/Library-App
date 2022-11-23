package com.libr.demo;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/writer")
public class RestApi {

    private final AtomicLong counter = new AtomicLong();
    Logger logger = org.slf4j.LoggerFactory.getLogger(RestApi.class);
    @Autowired
    private WriterRepository writerRepository;

    @GetMapping("/{id}, method = RequestMethod.GET")
    public ResponseEntity<Writer> findWriterById(@PathVariable(value = "id") Long id) {
        Optional<Writer> writer = writerRepository.findById(id);

        if(writer.isPresent()) {
            logger.info("Writer asked by ID");
            return ResponseEntity.ok().body(writer.get());
        } else {
            logger.info("Writer couldn't be asked by ID");
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/add")
    //localhost:8888/api/writer/add/
    public Writer addWriter(@RequestParam(value="name", defaultValue="---")  String name,@RequestParam(value="date", defaultValue="0000-00-00") String date) {
        System.out.println("Writer added");
        Writer writer = new Writer();
        writer.setId(counter.incrementAndGet());
        writer.setName(name);
        writer.setBirth(Date.valueOf(LocalDate.parse(date)));
        writerRepository.save(writer);
        System.out.println("Writer added");
        return writer;
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

    //testing
    //http://localhost:8080/greeting.
    //http://localhost:8080/greeting?name=User;
    @GetMapping("/greeting")
    public Writer greeting(@RequestParam(value="name", defaultValue="---") String name, @RequestParam(value="date", defaultValue="0000-00-00") String date) {

        //http://localhost:8080/api/writer/greeting?name=User&date=2019-01-01
        return new Writer(counter.incrementAndGet(),name,date);
    }
}