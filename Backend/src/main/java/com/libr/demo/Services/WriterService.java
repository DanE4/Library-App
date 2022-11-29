package com.libr.demo.Services;

import com.libr.demo.Entities.Book;
import com.libr.demo.Entities.Writer;

import java.time.LocalDate;
import java.util.Collection;

public interface WriterService {
    Collection<Writer> getWriters();

    Writer findByName(String name);

    void updateWriter(Writer w, int idi);

    Writer addWriter(String name, String birth, int id);

    Writer findById(int id);


    Boolean deleteWriterById(int id);

    Boolean deleteWriterByName(String name);
}
