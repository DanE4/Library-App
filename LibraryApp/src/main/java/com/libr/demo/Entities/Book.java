package com.libr.demo.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    private String title;
    @Column(name = "releaseyr")
    private int releaseyr;
    @Column(name = "writerid")
    private int writerid;
    public Book( String title, int releaseyr, int writer) {
        this.title = title;
        this.releaseyr = releaseyr;
        this.writerid = writer;
    }
}
