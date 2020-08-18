package com.beril.repository;
/* proje için depo oluşturuldu*/


import com.beril.model.Book;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@ComponentScan /* model paketindeki herşeyi tarar*/
public interface BookRepository extends JpaRepository<Book, Long> {
}

/*Book, üzerinde işlem yapacağımız entity, Long   bu entity’nin idsinin type’ı. (JPA) Spring data ve Hibernate
* kullanarak Java ile Sql in deposuna kaydeder*/



