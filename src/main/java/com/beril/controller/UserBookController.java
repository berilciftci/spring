package com.beril.controller;
/* Rest IP için postman kullanmak için / Dao yu çağır*/

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.beril.dao.UserBookDAO;
import com.beril.dao.UserDAO;
import com.beril.model.UserBook;
import com.beril.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.beril.dao.BookDAO;
import com.beril.model.Book;

@RestController
@RequestMapping("/library")     /*ile localhost:8080/library adresine GET
 isteği yaparak hazırladığımız sayfaya erişilmesini sağlıyoruz

Öncelikle web servis üretecek sınıfımızı @RestController notasyonu ile
        işaretliyoruz.Sınıfımızın içerisinde hangi metodun hangi URL’den gelen isteği karşılayacağını
        ise @RequestMapping notasyonu ile belirliyoruz. */

/*@RequestMapping(value={"/","library"})*/
@ComponentScan
public class UserBookController {

    @Autowired /* Dao sınınfıyla bu sınıfı bağlar.Bu sınıf bizim yapacağımız işlemleri görmesi için.*/
            UserBookDAO userBookDAO;

    @PostMapping("/userbooks")
    @CrossOrigin(origins = "http://localhost:3000")
    public UserBook createUserBook(@Valid @RequestBody UserBook ub) {

        return userBookDAO.save(ub);
    }
    @GetMapping("/userbooks")
    @CrossOrigin(origins ="http://localhost:3000")

    public List<UserBook> getAllBooks(){
        return userBookDAO.findAll();
    }

    @GetMapping("/userbooks/{id}")                                                       /*///////////////////*/
    @CrossOrigin(origins ="http://localhost:3000")
    public List<UserBook> getBooksById(@PathVariable(value = "id") Long modelId) {
        List<UserBook> userBooks = userBookDAO.findAll();
        List<UserBook> returnUserBooks = new ArrayList<>();
        for(int i=0;i<userBooks.size();i++) {
            if (userBooks.get(i).getUser().getId().equals(modelId)) {
                returnUserBooks.add(userBooks.get(i));
            }
        }
        return returnUserBooks;
    }
    @GetMapping("/findUser/{id}")                                                       /*///////////////////*/
    @CrossOrigin(origins ="http://localhost:3000")
    public List<UserBook> getFindUserById(@PathVariable(value = "id") Long modelId) {
        List<UserBook> userBooks = userBookDAO.findAll();
        List<UserBook> returnUserBooks = new ArrayList<>();
        for(int i=0;i<userBooks.size();i++) {
            if (userBooks.get(i).getBook().getId().equals(modelId)) {
                returnUserBooks.add(userBooks.get(i));
            }
        }
        return returnUserBooks;
    }



    @DeleteMapping("/userbooks/{id}")
    @CrossOrigin(origins ="http://localhost:3000")

    public ResponseEntity<UserBook> deleteUserBook(@PathVariable(value="id") Long ubid){

        UserBook ub=userBookDAO.findOne(ubid);
        if(ub==null) {
            return ResponseEntity.notFound().build();
        }
        userBookDAO.delete(ub);

        return ResponseEntity.ok().build();


    }

}

