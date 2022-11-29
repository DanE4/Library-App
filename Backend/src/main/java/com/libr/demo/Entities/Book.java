package com.libr.demo.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Writer.class)
    @JsonBackReference
    @JoinColumn(name = "writerid")
    @ToString.Exclude
    private Writer writer;

    public Book(String title, int releaseyr, Writer writer) {
        this.title = title;
        this.releaseyr = releaseyr;
        this.writer = writer;
    }

    public Book(int id, String title, int releaseyr, Writer writer) {
        this.id = id;
        this.title = title;
        this.releaseyr = releaseyr;
        this.writer = writer;
    }
}
