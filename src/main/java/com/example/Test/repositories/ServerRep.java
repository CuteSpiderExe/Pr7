package com.example.Test.repositories;

import com.example.Test.models.Server;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServerRep extends CrudRepository<Server, Long> {


    public List<Server> findByNameContains(String name);
    public List<Server> findByName(String name);
}
