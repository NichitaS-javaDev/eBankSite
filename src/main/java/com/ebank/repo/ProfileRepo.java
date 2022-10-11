package com.ebank.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ebank.model.Profile;

@Repository
public interface ProfileRepo extends CrudRepository<Profile, String>{

}
