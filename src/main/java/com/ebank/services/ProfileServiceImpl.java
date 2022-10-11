package com.ebank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebank.model.Profile;
import com.ebank.repo.ProfileRepo;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	private ProfileRepo repo;
	
	@Override
	public Profile findByLogin(String login) {
		return repo.findById(login).orElse(null);
	}

	@Override
	public Profile save(Profile profile) {
		return repo.save(profile);
	}
}
