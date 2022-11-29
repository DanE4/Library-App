package com.libr.demo.Repositories;

import com.libr.demo.Entities.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Integer> {
    Writer findByName(String name);
    Writer findById(int id);
    void deleteById(int id);
}

