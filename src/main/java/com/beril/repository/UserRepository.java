package com.beril.repository;

import com.beril.model.Users;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
@ComponentScan

public interface UserRepository extends JpaRepository<Users,Long> {
}
