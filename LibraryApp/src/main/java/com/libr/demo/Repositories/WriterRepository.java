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
    /*
    @Modifying
    @Query("update Writer u set u.birth = :birth where u.id = :id")
    void updateWriterBirth(@Param(value = "id") int id, @Param(value = "birth") String birth);
    */
    @Modifying
    @Query("update Writer u set u.name = :name where u.id = :id")
    void updateWriterName(@Param(value = "id") int id, @Param(value = "name") String name);

    void deleteById(int id);
}

