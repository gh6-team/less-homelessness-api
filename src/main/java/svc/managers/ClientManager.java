package svc.managers;

import org.springframework.stereotype.Component;
import svc.data.clients.ClientDAO;
import svc.models.Client;

import javax.inject.Inject;

@Component
public class ClientManager {
	
	@Inject
	private ClientDAO clientDAO;
	
	public Client saveClient(Client client)
	{
		client = clientDAO.saveClient(client);
		return client;
	}

    public Client GetClientById(Long id) {
        return clientDAO.getClientById(id);
    }
}
