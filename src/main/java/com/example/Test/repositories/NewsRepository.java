package com.example.Test.repositories;

import com.example.Test.models.News;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {
    public List<News> findByTitle(String title);
    public List<News> findByTitleContains(String title);
}
