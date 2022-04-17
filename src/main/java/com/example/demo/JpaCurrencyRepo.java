package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface JpaCurrencyRepo extends JpaRepository<Currencies, Integer> {
    List<Currencies> readCurenciesBySimplyId(String simplyId);
}

