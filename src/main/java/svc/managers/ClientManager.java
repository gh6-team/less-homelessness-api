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
		if(client.id == 0)
		{
			client = clientDAO.createClient(client);
		}
		else
		{
			clientDAO.updateClient(client);
		}
		return client;
	}
}
