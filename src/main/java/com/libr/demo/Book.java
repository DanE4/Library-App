package com.libr.demo;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int releaseYear;
    private int writerId;
    public Book(Long id, String title, int releaseYear, int writerId) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.writerId = writerId;
    }
}
