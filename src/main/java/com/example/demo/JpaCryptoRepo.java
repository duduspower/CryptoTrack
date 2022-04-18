package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface JpaCryptoRepo extends JpaRepository<Crypto,Integer> {
    List<Crypto> readCryptoByName(String simplyId);
}
