package com.ebank.dao;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ebank.model.Client;

@Component
public class ClientDAO {
	
	//@Autowired
	//Client client;
	List<Client> clientsList;
	
	{
		clientsList = new LinkedList<Client>();
		clientsList.add(new Client().createFakeClient());
		clientsList.add(new Client().createFakeClient());
		clientsList.add(new Client().createFakeClient());
		clientsList.add(new Client().createFakeClient());
		clientsList.add(new Client().createFakeClient());
	}
	
	public Client displayInfo(int id) {
		return clientsList.stream().filter(client -> client.getId() == id).findAny().orElse(null);
	}

}
