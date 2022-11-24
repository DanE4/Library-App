package com.libr.demo;

import com.libr.demo.Entities.Book;
import com.libr.demo.Entities.Writer;
import com.libr.demo.Services.BookService;
import com.libr.demo.Services.DB;
import com.libr.demo.Services.WriterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



	@Bean
	public CommandLineRunner demo(WriterService writerService, BookService bookService) {
		return (args -> {
			/*
			DB db = new DB();
			db.eraseDB();
			AtomicLong id = new AtomicLong(0);
			writerService.addWriter(new Writer(id.incrementAndGet(), "ember2", LocalDate.of(1964, 6, 22)));
			writerService.addWriter(new Writer(id.incrementAndGet(), "ember3", LocalDate.of(1964, 6, 22)));
			writerService.addWriter(new Writer(id.incrementAndGet(), "ember4", LocalDate.of(1964, 6, 22)));
			AtomicLong id2 = new AtomicLong(0);
			bookService.addBook(new Book(id2.incrementAndGet(), "title1", 2011, 1));
			bookService.addBook(new Book(id2.incrementAndGet(), "title2", 2014, 1));
			bookService.addBook(new Book(id2.incrementAndGet(), "title3", 2015, 2));
			bookService.addBook(new Book(id2.incrementAndGet(), "title4", 2016, 3));
			bookService.addBook(new Book(id2.incrementAndGet(), "title5", 2017, 3));
			bookService.addBook(new Book(id2.incrementAndGet(), "title6", 2018, 3));
		*/
		});
	}
}