package com.example.accessingdatamysql;

import java.util.List;
import java.util.Optional;

import com.example.accessingdatamysql.User;

public interface UserService {

    List <User> findAll();

    void save( User user);

     //Optional <User> findById(Long id);
    void delete(long id);

	Optional<User> findById(Long id);
}
