package svc.controllers;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import svc.logging.LogSystem;
import svc.managers.ClientManager;
import svc.models.Client;

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
}
