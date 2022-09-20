package com.example.Test.repositories;

import com.example.Test.models.Dota;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DotaRep extends CrudRepository<Dota, Long> {


    public List<Dota> findByNameContains(String name);
    public List<Dota> findByName(String name);
}