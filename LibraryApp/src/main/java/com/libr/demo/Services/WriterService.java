package com.libr.demo.Services;

import com.libr.demo.Entities.Writer;
import com.libr.demo.Repositories.BookRepository;
import com.libr.demo.Repositories.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WriterService {
    private final WriterRepository writerRepository;
    private final BookRepository bookRepository;
    @Autowired
    public WriterService(WriterRepository writerRepository, BookRepository bookRepository) {
        this.writerRepository = writerRepository;
        this.bookRepository = bookRepository;
    }





    /*
    public void updateWriterBirth(int id, String birth){
        writerRepository.updateWriterBirth(id, birth);
    }
    */
    public void updateWriterName(int id, String name){
        writerRepository.updateWriterName(id, name);
    }
    public void deleteById(int id){
        writerRepository.deleteById(id);
    }
    public List<Writer> getWriters() {
        return writerRepository.findAll();
    }
    public Writer findByName(String name) {
        return writerRepository.findByName(name);
    }
    /////////////////////////////////
    public Optional<Writer> addWriter(Writer writer) {
        if (writerRepository.findByName(writer.getName()) == null) {
            writerRepository.save(writer);
            return Optional.of(writer);
        }
        return Optional.empty();
    }
    /////////////////////////////////
    public Writer findById(int id) {
        return writerRepository.findById(id);
    }

    //COOL
    public void updateWriter(int id, LocalDate birth) {
            Writer tmpwriter = writerRepository.findById(id);
            tmpwriter.setBirth(Date.valueOf(birth));
            writerRepository.save(tmpwriter);
            //birth only
    }

    //COOOOL
    public void deleteWriterById(int id) {
        bookRepository.deleteAllByWriterid(id);
        writerRepository.deleteById(id);
    }


    //COOOOL
    public void deleteWriterByName(String name) {
        int id = writerRepository.findByName(name).getId();
        bookRepository.findAll().stream().filter(book->book.getWriterid()==id)
                .forEach(book-> System.out.println(book.getTitle()));
        bookRepository.findAll().stream().filter(book->book.getWriterid()==id)
                .forEach(bookRepository::delete);
        writerRepository.deleteById(writerRepository.findByName(name).getId());
    }
    /////////////////////////////////





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
    */
}
