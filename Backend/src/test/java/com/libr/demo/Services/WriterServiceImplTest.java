package com.libr.demo.Services;

import com.libr.demo.Entities.Book;
import com.libr.demo.Entities.Writer;
import com.libr.demo.Repositories.BookRepository;
import com.libr.demo.Repositories.WriterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@Component
class WriterServiceImplTest {
    @InjectMocks
    private WriterServiceImpl writerService;
    @Mock
    private WriterRepository writerRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getWriters() {
        when(writerRepository.findAll()).thenReturn(List.of(new Writer("name", LocalDate.of(2000, 1, 1))));
        List<Writer> writers = writerService.getWriters();
        assertEquals(1, writers.size());
    }
    @Test
    void willReturnNullWhenWriterNotFoundByName() {
        when(writerRepository.findByName("name")).thenReturn(null);
        Writer writer = writerService.findByName("name");
        assertNull(writer);
    }
    @Test
    void willReturnExceptionWhenWriterNotFoundByName() {
        when(writerRepository.findByName("name")).thenThrow(new RuntimeException());
        Writer writer = writerService.findByName("name");
        assertNull(writer);
    }

    @Test
    void findByName() {
        Writer writer = new Writer("name", LocalDate.of(2000, 1, 1));
        when(writerRepository.findByName("name")).thenReturn(writer);
        assertEquals(writer, writerService.findByName("name"));
    }
    @Test
    void findById() {
        Writer writer = new Writer("name", LocalDate.of(2000, 1, 1));
        when(writerRepository.findById(1)).thenReturn(writer);
        assertEquals(writer, writerService.findById(1));
    }
    @Test
    void willReturnNullWhenWriterNotFoundById() {
        when(writerRepository.findById(1)).thenReturn(null);
        Writer writer = writerService.findById(1);
        assertNull(writer);
    }
    @Test
    void willReturnExceptionWhenWriterNotFoundById() {
        when(writerRepository.findById(1)).thenThrow(new RuntimeException());
        Writer writer = writerService.findById(1);
        assertNull(writer);
    }
    @Test
    void willReturnWriterWhenWriterAdded() {
        Writer writer = new Writer("name", LocalDate.of(2000, 1, 1));
        when(writerRepository.save(writer)).thenReturn(writer);
        assertEquals(writer, writerService.addWriter("name", "2000-01-01",1));
    }

    @Test
    void deleteWriterById() {
        Writer writer = new Writer("name", LocalDate.of(2000, 1, 1));
        writerRepository.save(writer);
        writerService.deleteWriterById(1);
        assertNull(writerRepository.findByName("name"));
    }
    @Test
    void willReturnExceptionWhenWriterNotFoundByIdToDelete() {
        when(writerRepository.findById(1)).thenThrow(new RuntimeException());
        writerService.deleteWriterById(1);

    }
    @Test
    void willReturnTrueWhenWriterFoundByIdToDelete() {
        Writer writer = new Writer("name", LocalDate.of(2000, 1, 1));
        writerRepository.save(writer);
        assertTrue(writerService.deleteWriterById(1));
    }
    @Test
    void updateWriter() {
    }

    @Test
    void addWriter() {
        writerService.addWriter("name","2000-01-01",1);
        ArgumentCaptor<Writer> argumentCaptor = ArgumentCaptor.forClass(Writer.class);
        verify(writerRepository).save(argumentCaptor.capture());
        Writer writer = argumentCaptor.getValue();
        assertEquals("name", writer.getName());
    }
    @Test
    void willUpdateWriterIfWriterFoundById() {
        Writer writer = new Writer("name", LocalDate.of(2000, 1, 1));
        writerRepository.save(writer);
        writerService.updateWriter(writer,1);
        ArgumentCaptor<Writer> argumentCaptor = ArgumentCaptor.forClass(Writer.class);
        verify(writerRepository).save(argumentCaptor.capture());
        Writer writer1 = argumentCaptor.getValue();
        assertEquals("name", writer1.getName());
    }
    @Test
    void deleteWriterByName() {
        Writer writer = new Writer("name", LocalDate.of(2000, 1, 1));
        writerRepository.save(writer);
        writerService.deleteWriterByName("name");
        assertNull(writerRepository.findByName("name"));
    }
    @Test
    void willDeleteWriterByName() {
        Writer writer = new Writer("name", LocalDate.of(2000, 1, 1));
        writerRepository.save(writer);
        assertTrue(writerService.deleteWriterByName("name"));
    }
    @Test
    void willReturnFalseAndThrowExceptionWhenWriterNotFoundByNameToDelete() {
        when(writerRepository.findByName("name")).thenThrow(new RuntimeException());
        assertFalse(writerService.deleteWriterByName("name"));
    }
}