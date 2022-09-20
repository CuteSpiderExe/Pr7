package com.example.Test.repositories;

import com.example.Test.models.Atribut;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AtributRep extends CrudRepository<Atribut, Long> {


    public List<Atribut> findByNameContains(String name);
    public List<Atribut> findByName(String name);
}