package com.libr.demo;

import com.libr.demo.Services.DB;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Client {
    public static void main(String[] args) throws SQLException {
        System.out.println("START");
        DB db= new DB();
        db.connect();
        System.out.println("Connection to DB established on: "+ DB.DB_URL);
        db.eraseDB();
        db.addWriter(new Writer(1L,"J.K.Rowling", "1965-07-31"));
        db.addWriter(new Writer(2L,"Stephen King", "1947-09-21"));
        db.addWriter(new Writer(3L,"George R.R. Martin", "1948-09-20"));
        db.addWriter(new Writer(4L,"J.R.R. Tolkien","1892-01-03"));
        db.addWriter(new Writer(5L,"Dan Brown", "1964-06-22"));
        db.addBook(new Book(1L,"Harry Potter and the Philosopher's Stone",1997,1));
        db.addBook(new Book(2L,"Harry Potter and the Chamber of Secrets",1998,1));
        db.addBook(new Book(3L,"Harry Potter and the Prisoner of Azkaban",1999,1));
        db.addBook(new Book(4L,"Harry Potter and the Goblet of Fire",2000,1));
        db.addBook(new Book(5L,"Harry Potter and the Order of the Phoenix",2003,1));
        db.addBook(new Book(6L,"Harry Potter and the Half-Blood Prince",2005,1));
        db.addBook(new Book(7L,"Harry Potter and the Deathly Hallows",2007,1));
        db.addBook(new Book(8L,"The Shining",1977,2));
        db.addBook(new Book(9L,"It",1986,2));
        db.addBook(new Book(10L,"The Stand",1978,2));
        db.addBook(new Book(11L,"The Green Mile",1996,2));
        db.addBook(new Book(12L,"The Dark Tower",2003,2));
        db.addBook(new Book(13L,"The Shining",1977,2));
        db.addBook(new Book(14L,"A Game of Thrones",1996,3));
        db.addBook(new Book(15L,"A Clash of Kings",1998,3));
        db.addBook(new Book(16L,"A Storm of Swords",2000,3));
        db.addBook(new Book(17L,"A Feast for Crows",2005,3));
        db.addBook(new Book(18L,"A Dance with Dragons",2011,3));
        db.addBook(new Book(19L,"The Fellowship of the Ring",1954,4));
        db.addBook(new Book(20L,"The Two Towers",1954,4));
        db.addBook(new Book(21L,"The Return of the King",1955,4));
        db.addBook(new Book(22L,"Angels & Demons",2000,5));
        db.addBook(new Book(23L,"The Da Vinci Code",2003,5));
        db.addBook(new Book(24L,"The Lost Symbol",2009,5));
        db.addBook(new Book(25L,"Inferno",2013,5));
        db.addBook(new Book(26L,"Origin",2017,5));
        db.deleteWriter("Stephen King");

        db.ListWriters().stream().forEach(System.out::println);
        System.out.println("END");



    }
}
