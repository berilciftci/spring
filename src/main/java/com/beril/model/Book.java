package com.beril.model;
/*Tablo içide kullanılacak isimler belirlendi id:primaryKey     */

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity  /*book modeli aynı zamanda oluşturacağımız db için bir Entitiy*/
@Table(name = "book")
@EntityListeners(AuditingEntityListener.class)
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) /*Kendiliğinden artması için (id:primary key ) */
    private Long id;

    private String name;

    private String year;

    private String author;  /* Getter and setter */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

