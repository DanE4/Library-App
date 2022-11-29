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
    public void updateWriter(Writer w, int idi) {
        System.out.println(w);
        int id =findById(idi).getId();
        System.out.println(id);
        Writer newWriter=new Writer(id,w.getName(),w.getBirth());
        System.out.println(newWriter);
        writerRepository.save(newWriter);
    }

    @Override
    public Writer addWriter(String name, String birth,int id) {
        Writer writer = new Writer(name, LocalDate.parse(birth));
        logger.info("Adding writer: " + writer.getName());
        if (findById(id) == null) {
            try {
                writerRepository.save(writer);
                logger.info("Writer added: "+writer.getName());
            } catch (Exception e) {

                logger.warning("Error adding writer: "+writer.getName()+", Exception: "+e.getMessage());
            }
            logger.info("Writer added: "+writer.getName());
            return writer;
        }
        else{
            try {
                updateWriter(writer,id);
                logger.info("Writer udating: "+writer.getName());
                System.out.println("Writer updated: "+writer.getName());
            }
            catch (Exception e){
                System.out.println("Error updating writer: "+writer.getName()+", Exception: "+e.getMessage());
                logger.warning("Error updating writer: "+writer.getName()+", Exception: "+e.getMessage());
            }
        }
        return writer;
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
    public Boolean deleteWriterById(int id) {
        try {
            logger.info("Deleting all books by writerid: "+id);
            bookRepository.deleteAllByWriterId(id);
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
            bookRepository.findAll().stream().filter(book->book.getWriter().getId()==id)
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
