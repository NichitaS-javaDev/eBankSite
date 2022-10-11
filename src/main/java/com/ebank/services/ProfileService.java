package com.ebank.services;

import com.ebank.model.Profile;

public interface ProfileService {
	public Profile findByLogin(String login);
	public Profile save(Profile profile);
}
