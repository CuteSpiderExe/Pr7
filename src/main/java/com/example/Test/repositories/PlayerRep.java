package com.example.Test.repositories;

import com.example.Test.models.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRep extends CrudRepository<Player, Long> {


    public List<Player> findByNameContains(String name);
    public List<Player> findByName(String name);
}
