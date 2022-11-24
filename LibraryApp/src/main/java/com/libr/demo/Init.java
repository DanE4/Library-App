package com.libr.demo;

import com.libr.demo.Entities.Book;
import com.libr.demo.Entities.Writer;
import com.libr.demo.Services.DB;

import java.sql.SQLException;
import java.time.LocalDate;

public class Init {
    public static void main(String[] args) throws SQLException {
        System.out.println("START");
        DB db= new DB();
        db.connect();
        System.out.println("Connection to DB established on: "+ DB.DB_URL);
        db.eraseDB();
        db.addWriter(new Writer("J.K. Rowling", LocalDate.of(1965, 7, 31)));
        db.addWriter(new Writer("Stephen King", LocalDate.of(1947, 9, 21)));
        db.addWriter(new Writer("George R.R. Martin", LocalDate.of(1948, 9, 20)));
        db.addWriter(new Writer("J.R.R. Tolkien", LocalDate.of(1892, 1, 3)));
        db.addWriter(new Writer("Dan Brown", LocalDate.of(1964, 6, 22)));
        db.addBook(new Book("Harry Potter and the Philosopher's Stone", 1997, 1));
        db.addBook(new Book("Harry Potter and the Chamber of Secrets", 1998, 1));
        db.addBook(new Book("Harry Potter and the Prisoner of Azkaban", 1999, 1));
        db.addBook(new Book("Harry Potter and the Goblet of Fire", 2000, 1));
        db.addBook(new Book("Harry Potter and the Order of the Phoenix", 2003, 1));
        db.addBook(new Book("Harry Potter and the Half-Blood Prince", 2005, 1));
        db.addBook(new Book("Harry Potter and the Deathly Hallows", 2007, 1));
        db.addBook(new Book("The Shining", 1977, 2));
        db.addBook(new Book("It", 1986, 2));
        db.addBook(new Book("The Stand", 1978, 2));
        db.addBook(new Book("The Dark Tower", 2003, 3));
        db.addBook(new Book("A Game of Thrones", 1996, 3));
        db.addBook(new Book("A Clash of Kings", 1998, 3));
        db.addBook(new Book("A Storm of Swords", 2000, 3));
        db.addBook(new Book("A Feast for Crows", 2005, 3));
        db.addBook(new Book("A Dance with Dragons", 2011, 3));
        db.addBook(new Book("The Fellowship of the Ring", 1954, 4));
        db.addBook(new Book("The Two Towers", 1954, 4));
        db.addBook(new Book("The Return of the King", 1955, 4));
        db.addBook(new Book("The Da Vinci Code", 2003, 5));
        db.addBook(new Book("Angels & Demons", 2000, 5));
        db.addBook(new Book("The Lost Symbol", 2009, 5));
        db.addBook(new Book("Inferno", 2013, 5));
        db.addBook(new Book("Origin", 2017, 5));

        db.deleteWriter("Stephen King");

        db.ListWriters().stream().forEach(System.out::println);
        System.out.println("END");

    }
}
