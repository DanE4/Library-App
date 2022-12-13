package com.libr.demo.Services;

import com.libr.demo.Entities.Writer;

import java.time.LocalDate;
import java.util.Collection;

public interface WriterService {
    Collection<Writer> getWriters();

    Writer findByName(String name);

    Writer addWriter(String name, String birth);

    Writer findById(int id);

    Boolean updateWriterBirth(int id, LocalDate birth);

    Boolean deleteWriterById(int id);

    Boolean deleteWriterByName(String name);
}
