package com.example.Test.repositories;


import com.example.Test.models.Spos;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SposRep extends CrudRepository<Spos, Long> {


    public List<Spos> findByNameContains(String name);
    public List<Spos> findByName(String name);
}