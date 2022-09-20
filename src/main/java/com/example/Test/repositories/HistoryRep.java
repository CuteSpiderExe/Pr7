package com.example.Test.repositories;

import com.example.Test.models.History;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRep extends CrudRepository<History, Long> {


    public List<History> findByNameContains(String name);
    public List<History> findByName(String name);
}