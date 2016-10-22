package svc.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import svc.logging.LogSystem;
import svc.managers.ClientManager;
import svc.models.Client;

import javax.inject.Inject;

@RestController
@EnableAutoConfiguration
@RequestMapping("/client")
public class ClientController {
	@Inject
	ClientManager clientManager;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	Client SaveClient(@RequestBody Client client)
	{
		if(client == null)
		{
			LogSystem.LogEvent("Null opportunity passed to post.");
			return null;
		}
		
		return clientManager.saveClient(client);
	}

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Client GetClient(@PathVariable("id") Long id) {
        if (id == null) {
            LogSystem.LogEvent("Null id passed to controller");
        }

        return clientManager.GetClientById(id);
    }

}
