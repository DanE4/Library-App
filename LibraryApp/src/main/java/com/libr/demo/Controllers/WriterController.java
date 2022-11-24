package com.libr.demo.Controllers;

import com.libr.demo.Services.WriterService;
import com.libr.demo.Entities.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/writer")
public class WriterController {

    //spring bean
    private final WriterService writerService;

    @Autowired
    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    //testing jsp
    @RequestMapping("welcome")
    public String welcome(){
        System.out.println("Welcome");
        return "welcome.jsp";
    }
    /*
    @GetMapping("/list")
    public List<Writer> getWriters() {
        return writerService.getWriters();
    }

     */
    //COOL
    //http://localhost:8888/api/writer/find/Dan Brown
    @GetMapping("/find/{name}")
    public Writer findByName(@PathVariable String name) {
        return writerService.findByName(name);
    }
    //COOL
    //http://localhost:8888/api/writer/findid/2
    @GetMapping("/findid/{id}")
    public Writer findById(@PathVariable int id) {
        return writerService.findById(id);
    }
    //COOOOOL
    @GetMapping("/writers")
    public List<Writer> list() {
        return writerService.getWriters();
    }

    //COOOOOL
    //http://localhost:8888/api/writer/add/Dan Brown/1964-06-22
    @PostMapping("/add/{name}/{birth}")
    public Optional<Writer> add(@PathVariable String name, @PathVariable String birth) {
        if(name == null || birth == null) {
            System.out.println("Name or birth is null");
            return Optional.empty();
        }
        return writerService.addWriter(new Writer(name, LocalDate.parse(birth)));
    }
    //COOOOOL
    //http://localhost:8888/api/writer/update/Dan Brown/1964-06-22
    @PutMapping("/update/{id}/{birth}")
    public void updateWriterBirth(@PathVariable("id") int id,@PathVariable("birth") String birth) {
        writerService.updateWriter(id, LocalDate.parse(birth));
        System.out.println("Updated:" + id);
    }


    //nooooooooooooOOOOOOOOOOOOOOOOOOOOOOOO not good because of
    //http://localhost:8888/api/writer/delete/Dan Brown
    @DeleteMapping("/delete/{name}")
    public void deleteWriterByName(@PathVariable String name) {
        if(name == null) {
            System.out.println("Name is null");
            return;
        }
        writerService.deleteWriterByName(name);
    }

    /*
    @PostMapping("/writer")
    public Writer saveWriter(@RequestBody Writer writer) {
        System.out.println("/writer");
        return writerService.saveWriter(writer);
    }
    */


/*
    //http://localhost:8888/api/writer/delete/2
    @DeleteMapping("/deleteid/{id}")
    public String deleteWriterById(@PathVariable("id") int id) {
        System.out.println("/deleteid/{id}");
        writerService.deleteWriterById(id);
        return "Deleted Successfully";
    }*/
}