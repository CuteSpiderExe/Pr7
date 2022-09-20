package com.example.Test.repositories;

import com.example.Test.models.Mode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModeRep extends CrudRepository<Mode, Long> {


    public List<Mode> findByNameContains(String name);
    public List<Mode> findByName(String name);
}
