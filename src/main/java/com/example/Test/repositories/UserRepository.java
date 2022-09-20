package com.example.Test.repositories;



import com.example.Test.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String login);
    //public List<User> findByUsername(String login);
}

