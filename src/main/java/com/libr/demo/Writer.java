package com.libr.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Writer")
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date birth;
    public Writer(Long id, String name, String date) {
        this.id = id;
        this.name = name;

        LocalDate localDate = LocalDate.parse(date);

        this.birth = Date.valueOf(localDate);
    }

}
