package com.libr.demo.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Writer")
public class Writer {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Birthdate should not be empty")
    private Date birth;
    public Writer( String name, LocalDate date) {
        this.name = name;
        this.birth = Date.valueOf(date);
    }
    public LocalDate getLocDat() {
        return birth.toLocalDate();
    }
}
