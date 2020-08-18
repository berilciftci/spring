package com.beril.controller;

import com.beril.dao.UserDAO;
import com.beril.model.Users;
import com.beril.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/library")
public class UserController {

    @Autowired
    UserDAO userDAO;


    @GetMapping("/users")
    @CrossOrigin(origins="http://localhost:3000")
    public List<Users> getAllUsers(){
        return userDAO.findAll();
    }

    @PostMapping("/users")
    @CrossOrigin(origins="http://localhost:3000")          /*React ve spring boot bağlamak için @CrossOrigin*/
    public Users createUsers(@Valid @RequestBody Users u) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(u.getPassword());
        u.setPassword(encodedPassword);
        return userDAO.save(u);
    }

    @GetMapping("/users/{id}")
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<Users> getUsersById(@PathVariable(value="id") Long uid){

        Users u=userDAO.findOne(uid);

        if(u==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(u);

    }

    @PutMapping("/users/{id}")
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<Users> updateUsers(@PathVariable(value="id") Long uid,@Valid @RequestBody Users uDetails){

        Users u=userDAO.findOne(uid);
        if(u==null) {
            return ResponseEntity.notFound().build();
        }



        u.setUsername(uDetails.getUsername());
        u.setPassword(uDetails.getPassword());


        Users updateUser=userDAO.save(u);
        return ResponseEntity.ok().body(updateUser);



    }


    @GetMapping( "byusername/{userName}")
    @CrossOrigin(origins="http://localhost:3000")
    public Long getIdByUserName(@PathVariable(value = "userName") String userName) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println(sdf.format(cal.getTime())+" -> "+userName+" giriş yaptı");        Long returnId = 0l;

            userName = userName.toLowerCase();
            List<Users> users = userDAO.findAll();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().toLowerCase().equals(userName)) {
                    returnId = users.get(i).getId();
                }
            }

        return returnId;
    }



    @DeleteMapping("/users/{id}")
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<Users> deleteUser(@PathVariable(value="id") Long uid){

        Users u=userDAO.findOne(uid);
        if(u==null) {
            return ResponseEntity.notFound().build();
        }
        userDAO.delete(u);

        return ResponseEntity.ok().build();


    }




}
