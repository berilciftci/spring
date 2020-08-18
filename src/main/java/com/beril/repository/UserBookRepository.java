package com.beril.repository;


import com.beril.model.UserBook;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
@ComponentScan
public interface UserBookRepository extends JpaRepository<UserBook, Long> {

}