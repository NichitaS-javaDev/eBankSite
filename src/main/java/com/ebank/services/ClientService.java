package com.ebank.services;

import com.ebank.model.Client;

public interface ClientService {
	public Client findById(int id);
	public Client save(Client client);
}
