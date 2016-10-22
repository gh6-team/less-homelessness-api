package svc.managers;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import svc.data.clients.ClientDAO;
import svc.models.Client;

@Component
public class ClientManager {
	
	@Inject
	private ClientDAO clientDAO;
	
	public Client createOrUpdateClient(Client client)
	{
		throw new RuntimeException();
		//return client;
	}
}
