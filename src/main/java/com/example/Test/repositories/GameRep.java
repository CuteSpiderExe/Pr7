package com.example.Test.repositories;

import com.example.Test.models.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRep extends CrudRepository<Game, Long> {


    public List<Game> findByNameContains(String name);
    public List<Game> findByName(String name);
}
