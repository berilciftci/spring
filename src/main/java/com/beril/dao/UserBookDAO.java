package com.beril.dao;

import java.util.List;

import com.beril.model.UserBook;
import com.beril.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.beril.model.Book;
import com.beril.repository.BookRepository;


@Service
@ComponentScan

public class UserBookDAO {

    @Autowired
    UserBookRepository userBookRepository;



    public UserBook save(UserBook ub) {
        return userBookRepository.save(ub);
    }




    public List<UserBook> findAll(){
        return userBookRepository.findAll();
    }



    public UserBook findOne(Long ubid) {
        return userBookRepository.findOne(ubid);
    }

    public void delete(UserBook ub) {
        userBookRepository.delete(ub);
    }
}

