package com.libr.demo.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int  id;
    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    private String title;
    @Column(name = "releaseyr")
    @NotEmpty(message = "Release year should not be empty")
    private int releaseyr;
    @Column(name = "writerid")
    @NotEmpty(message = "Writer id should not be empty")
    private int writerid;
    public Book( String title, int releaseyr, int writer) {
        this.title = title;
        this.releaseyr = releaseyr;
        this.writerid = writer;
    }
}
