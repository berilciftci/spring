package com.beril.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.beril.model.Book;
import com.beril.repository.BookRepository;


@Service
@ComponentScan
/* db de tuttuğumuz book verisine erişim nesnesi */
public class BookDAO {

    @Autowired
    BookRepository bookRepository;  /* book repository e bağladık*/

    /*to save/add a Book*/

    public Book save(Book b) {
        return bookRepository.save(b);
    }


    /* search all books*/

    public List<Book> findAll(){
        return bookRepository.findAll();
    }


    /*get a book by id*/
    public Book findOne(Long bid) {
        return bookRepository.findOne(bid);
    }


    /*delete a book*/

    public void delete(Book b) {
        bookRepository.delete(b);
    }
}

