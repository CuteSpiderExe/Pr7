package com.example.Test.repositories;


import com.example.Test.models.Items;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemsRep extends CrudRepository<Items, Long> {


    public List<Items> findByNameContains(String name);
    public List<Items> findByName(String name);
}
