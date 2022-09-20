package com.example.Test.repositories;

import com.example.Test.models.Ranks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RanksRep extends CrudRepository<Ranks, Long> {


    public List<Ranks> findByNameContains(String name);
    public List<Ranks> findByName(String name);
}
