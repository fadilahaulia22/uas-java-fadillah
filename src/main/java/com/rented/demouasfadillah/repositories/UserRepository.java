package com.rented.demouasfadillah.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rented.demouasfadillah.models.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer>{
    List<User> findByUsernameAndPassword(String username, String password);
}
