package com.ebank.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ebank.model.Client;

@Repository
public interface ClientRepo extends CrudRepository<Client, Integer>{

}
