package com.libr.demo.Repositories;

import com.libr.demo.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    void deleteAllByWriterId(int id);

    Book findByTitle(String title);
    Book findById(int id);

    void deleteByTitle(String title);
}

