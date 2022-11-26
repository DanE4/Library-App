package com.libr.demo.Services;

import com.libr.demo.Entities.Book;
import com.libr.demo.Entities.Writer;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB {



    public static final String DB_URL = "jdbc:mysql://localhost:3306/Library?allowPublicKeyRetrieval=true&useSSL=false";
    public static final String name="root";
    public static final String password="Rootpassword";
    //log with logback
    public void connect() {
        try {
            DriverManager.getConnection(DB_URL, name, password);
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void eraseDB() {
        try {
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM book");
            statement.executeUpdate("DELETE FROM writer");
            statement.executeUpdate("ALTER TABLE book AUTO_INCREMENT = 1");
            statement.executeUpdate("ALTER TABLE writer AUTO_INCREMENT = 1");
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBook(Book book) {
        try {
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("INSERT INTO book (id, title,releaseyr, writerid ) VALUES (?,?,?,?)");
            statement.setLong(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setInt(3, book.getReleaseyr());
            statement.setInt(4, book.getWriterid());
            statement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBook(Book book) {
        try{
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("DELETE FROM book WHERE id = ?");
            statement.setLong(1, book.getId());
            statement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(Book book) {
        try {
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("UPDATE book SET title = ?, releaseyr = ?, writerid = ? WHERE id = ?");
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getReleaseyr());
            statement.setInt(3, book.getWriterid());
            statement.setLong(4, book.getId());
            statement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWriter(Writer writer) {
        try {
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("INSERT INTO writer (id, name,birth) VALUES (?,?,?)");
            statement.setLong(1, writer.getId());
            statement.setString(2, writer.getName());
            statement.setDate(3, writer.getBirth());
            statement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteWriter(String writer) {
        try{
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("SELECT id FROM writer WHERE name = ?");
            statement.setString(1, writer);
            var resultSet = statement.executeQuery();
            resultSet.next();
            var id = resultSet.getInt(1);
            statement = connection.prepareStatement("DELETE FROM book WHERE writerid = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement = connection.prepareStatement("DELETE FROM writer WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Writer> ListWriters() {
        try {
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("SELECT * FROM writer");
            var resultSet = statement.executeQuery();
            var writers = new ArrayList<Writer>();
            while (resultSet.next()) {
                var writer = new Writer();
                writer.setId( resultSet.getInt(1));
                writer.setName(resultSet.getString(2));
                writer.setBirth(resultSet.getDate(3));
                writers.add(writer);
            }
            connection.close();
            return writers;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> ListBooks() {
        try {
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("SELECT * FROM book");
            var resultSet = statement.executeQuery();
            var books = new ArrayList<Book>();
            while (resultSet.next()) {
                var book = new Book();
                book.setId(resultSet.getInt(1));
                book.setTitle(resultSet.getString(2));
                book.setReleaseyr(resultSet.getInt(3));
                book.setWriterid(resultSet.getInt(4));
                books.add(book);
            }
            connection.close();
            return books;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
