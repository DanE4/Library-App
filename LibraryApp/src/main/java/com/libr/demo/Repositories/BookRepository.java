package com.libr.demo.Repositories;

import com.libr.demo.Entities.Book;
import com.libr.demo.Entities.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findById(int id);
    Book findByTitle(String title);

    void findByWriterid(int writerid);
    void deleteAllByWriterid(int id);
}

