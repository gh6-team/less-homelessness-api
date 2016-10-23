package svc.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import svc.logging.LogSystem;
import svc.managers.ClientManager;
import svc.models.Client;
import svc.models.ClientNeed;

import java.util.List;

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
	@RequestMapping(method = RequestMethod.GET)
	List<Client> GetClients(){
		return clientManager.getAllClients();
	}
	
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    ResponseEntity GetClient(@PathVariable("id") Integer id) {
        if (id == null) {
            LogSystem.LogEvent("Null id passed to controller");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Client client = clientManager.GetClientById(id);
        if(client == null)
        {
        	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/spdat")
    ResponseEntity GetSpdatScore(@PathVariable("id") Integer id) {
        if (id == null) {
            LogSystem.LogEvent("Null id passed to controller");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Client client = clientManager.GetClientById(id);
        if(client == null)
        {
        	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        
        int score = clientManager.getSpdatScore(client);
    	return new ResponseEntity<>(score, HttpStatus.OK);
    }
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/needs")
    void CreateNeed(@RequestBody ClientNeed client_need)
    {
    	clientManager.CreateNeed(client_need);
    }
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/need/{need_id}")
    void DeleteNeed(@PathVariable("id") Integer id, @PathVariable("need_id") Integer need_id)
    {
    	clientManager.DeleteNeed(need_id);
    }
}
