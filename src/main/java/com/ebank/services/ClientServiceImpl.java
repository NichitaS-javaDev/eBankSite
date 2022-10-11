package com.ebank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebank.model.Client;
import com.ebank.repo.ClientRepo;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepo repo;

	@Override
	public Client findById(int id) {
		return repo.findById(id).orElse(null); 
	}

	@Override
	public Client save(Client client) {
		return repo.save(client);
	}
}
