package com.libr.demo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Writer")
public class Writer {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Birthdate should not be empty")
    private Date birth;

    @OneToMany(mappedBy="id",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Book> books;

    public Writer( String name, LocalDate date) {
        this.name = name;
        this.birth = Date.valueOf(date);
    }

    public Writer(int id, String name, Date birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }

}
