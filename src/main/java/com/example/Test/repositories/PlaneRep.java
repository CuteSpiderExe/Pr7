package com.example.Test.repositories;

import com.example.Test.models.Plane;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaneRep extends CrudRepository<Plane, Long> {
    public List<Plane> findByName(String name);

    public List<Plane> findByNameContains(String name);
}