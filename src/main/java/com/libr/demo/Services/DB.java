package com.libr.demo.Services;

import com.libr.demo.Book;
import com.libr.demo.Writer;

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
    public void eraseDB() throws SQLException {
        try {
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Book");
            statement.executeUpdate("DELETE FROM Writer");
            statement.executeUpdate("ALTER TABLE Book AUTO_INCREMENT = 1");
            statement.executeUpdate("ALTER TABLE Writer AUTO_INCREMENT = 1");
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBook(Book book) throws SQLException {
        try {
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("INSERT INTO Book (id, title,`release`, writerid ) VALUES (?,?,?,?)");
            statement.setLong(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setInt(3, book.getReleaseYear());
            statement.setInt(4, book.getWriterId());
            statement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBook(Book book) throws SQLException {
        try{
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("DELETE FROM Book WHERE id = ?");
            statement.setLong(1, book.getId());
            statement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(Book book) throws SQLException {
        try {
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("UPDATE Book SET title = ?, `release` = ?, writerid = ? WHERE id = ?");
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getReleaseYear());
            statement.setInt(3, book.getWriterId());
            statement.setLong(4, book.getId());
            statement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWriter(Writer writer) throws SQLException {
        try {
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("INSERT INTO Writer (id, name,birth) VALUES (?,?,?)");
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

    public void deleteWriter(String writer) throws SQLException {
        try{
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("SELECT id FROM Writer WHERE name = ?");
            statement.setString(1, writer);
            var resultSet = statement.executeQuery();
            resultSet.next();
            var id = resultSet.getInt(1);
            statement = connection.prepareStatement("DELETE FROM Book WHERE writerid = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement = connection.prepareStatement("DELETE FROM Writer WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Writer> ListWriters() throws SQLException {
        try {
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("SELECT * FROM Writer");
            var resultSet = statement.executeQuery();
            var writers = new ArrayList<Writer>();
            while (resultSet.next()) {
                var writer = new Writer();
                writer.setId((long) resultSet.getInt(1));
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

    public List<Book> ListBooks() throws SQLException {
        try {
            var connection = DriverManager.getConnection(DB_URL, name, password);
            var statement = connection.prepareStatement("SELECT * FROM Book");
            var resultSet = statement.executeQuery();
            var books = new ArrayList<Book>();
            while (resultSet.next()) {
                var book = new Book();
                book.setId((long) resultSet.getInt(1));
                book.setTitle(resultSet.getString(2));
                book.setReleaseYear(resultSet.getInt(3));
                book.setWriterId(resultSet.getInt(4));
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
