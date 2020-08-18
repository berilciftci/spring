package com.beril.dao;

import com.beril.model.Users;
import com.beril.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@ComponentScan

public class UserDAO {
    @Autowired
    UserRepository userRepository;
    /*to save/add a user*/

    public Users save(Users u) {
        return userRepository.save(u);
    }


    /* search all user*/

    public List<Users> findAll(){
        return userRepository.findAll();
    }


    /*get an user by id*/
    public Users findOne(Long bid) {
        return userRepository.findOne(bid);
    }



    /*delete an user*/

    public void delete(Users u) {
        userRepository.delete(u);
    }

}
