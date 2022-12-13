package com.libr.demo.Services;

import com.libr.demo.Controllers.WriterController;
import com.libr.demo.Entities.Writer;
import com.libr.demo.Repositories.BookRepository;
import com.libr.demo.Repositories.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class WriterServiceImpl implements WriterService {
    private final Logger logger = Logger.getLogger(WriterController.class.getName());
    private final WriterRepository writerRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Writer> getWriters() {
        return writerRepository.findAll();
    }
    @Override
    public Writer findByName(String name) {
        try {
            if(writerRepository.findByName(name)!=null){
                logger.info("Writer found by name: "+name);
                return writerRepository.findByName(name);
            }
            else{
                logger.info("Writer not found by name: "+name);
                return null;
            }
        }
        catch (Exception e){
            logger.info("Writer not found by name, exception:  "+e.getMessage());
            return null;
        }
    }
    @Override
    public Writer addWriter(String name, String birth) {
        Writer writer = new Writer(name, LocalDate.parse(birth));
        try{
            if (writerRepository.findByName(writer.getName()) == null) {
                writerRepository.save(writer);
                logger.info("Writer added: " + writer.getName());
                return writer;
            }
            logger.warning("Writer not added: " + writer.getName());
            return null;
        } catch (Exception e) {
            logger.warning("Writer not added: " + writer.getName());
            return null;
        }
    }
    @Override
    public Writer findById(int id) {
        try {
            logger.info("Finding writer by Id: "+id);
            if(writerRepository.findById(id)!=null){
                logger.info("Writer found by id: "+id);
                return writerRepository.findById(id);
            }
            else{
                logger.info("Writer not found by id: "+id);
                return null;
            }
        }
        catch (Exception e){
            logger.info("Writer not found by id, exception:  "+e.getMessage());
            return null;
        }
    }
    @Override
    public Boolean updateWriterBirth(int id, LocalDate birth) {
        try {
            Writer tmpwriter = writerRepository.findById(id);
            try {
                tmpwriter.setBirth(Date.valueOf(birth));
                writerRepository.save(tmpwriter);
                return true;
            } catch (Exception e) {
                logger.warning("Error while updating writer birth: " + e.getMessage());
                return false;
            }
        }
        catch (Exception e){
            logger.warning("Writer not found by id: "+id);
            return false;
        }
    }
    @Override
    public Boolean deleteWriterById(int id) {
        try {
            logger.info("Deleting all books by writerid: "+id);
            bookRepository.deleteAllByWriterid(id);
        }
        catch (Exception e){
            logger.warning("Error while deleting books by writerid: "+id+", Exception: "+e.getMessage());
            return false;
        }
        try {
            logger.info("Deleting writer by id: "+id);
            writerRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            logger.warning("Error while deleting writer by id: "+id+ ", Exception: "+e.getMessage());
            return false;
        }
    }
    @Override
    public Boolean deleteWriterByName(String name) {
        try {
            logger.info("Deleting all books by writername: "+name);
            int id = writerRepository.findByName(name).getId();
            bookRepository.findAll().stream().filter(book->book.getWriterid()==id)
                    .forEach(bookRepository::delete);
        }
        catch (Exception e){
            logger.warning("Error while deleting books by writername: "+name+", Exception: "+e.getMessage());
            return false;
        }
        try {
            logger.info("Deleting writer by name: "+name);
            writerRepository.deleteById(writerRepository.findByName(name).getId());
            return true;
        }
        catch (Exception e){
            logger.warning("Error while deleting writer by name: "+name+ ", Exception: "+e.getMessage());
            return false;
        }
    }
        /* testing:

        bookRepository.findAll().stream().filter(book->book.getWriterid()==id)
                .forEach(book-> System.out.println(book.getTitle()));
         */
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
