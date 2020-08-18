package com.beril.controller;
/* Rest IP için postman kullanmak için / Dao yu çağır*/

import java.util.List;

import javax.validation.Valid;

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
public class BookController {

    @Autowired /* Dao sınınfıyla bu sınıfı bağlar.Bu sınıf bizim yapacağımız işlemleri görmesi için.*/
    BookDAO bookDAO;

    /*cors hatası*/

    @PostMapping("/books")
    @CrossOrigin(origins="http://localhost:3000")          /*React ve spring boot bağlamak için @CrossOrigin*/
    public Book createBook(@Valid @RequestBody Book b) {
        return bookDAO.save(b);
    }

    @GetMapping("/books")
    @CrossOrigin(origins="http://localhost:3000")
    public List<Book> getAllBooks(){
        return bookDAO.findAll();
    }

    @GetMapping("/books/{id}")
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<Book> getBookById(@PathVariable(value="id") Long uid){

        Book b=bookDAO.findOne(uid);

        if(b==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(b);

    }



    @PutMapping("/books/{id}")
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<Book> updateBooks(@PathVariable(value="id") Long bid,@Valid @RequestBody Book bDetails){

        Book b=bookDAO.findOne(bid);
        if(b==null) {
            return ResponseEntity.notFound().build();
        }



        b.setName(bDetails.getName());
        b.setYear(bDetails.getYear());
        b.setAuthor(bDetails.getAuthor());

        Book updateBook=bookDAO.save(b);
        return ResponseEntity.ok().body(updateBook);



    }

    @DeleteMapping("/books/{id}")
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<Book> deleteBook(@PathVariable(value="id") Long bid){

        Book b=bookDAO.findOne(bid);
        if(b==null) {
            return ResponseEntity.notFound().build();
        }
        bookDAO.delete(b);

        return ResponseEntity.ok().build();
    }

}



