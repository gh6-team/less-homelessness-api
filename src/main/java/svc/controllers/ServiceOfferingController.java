package svc.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import svc.managers.ServiceOfferingManager;
import svc.models.ServiceOffering;

@RestController
@EnableAutoConfiguration
@RequestMapping("/service-offering")
public class ServiceOfferingController {
	@Inject
	ServiceOfferingManager servicesManager;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<ServiceOffering> GetAllServiceOfferings() {
		return servicesManager.getAllServiceOfferings();
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/organization/{organization_id}")
	public List<ServiceOffering> GetServiceOfferings(@PathVariable("organization_id") int organization_id) {
		return servicesManager.getServiceOfferings(organization_id);
	}
	
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/client-need/{client_need_id}/assign")
    public void Assign(@PathVariable("id") int id, @PathVariable("client_need_id") int client_need_id)
    {
    	servicesManager.Assign(id,  client_need_id);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/client-need/{client_need_id}/unassign")
    public void Unassign(@PathVariable("id") int id, @PathVariable("client_need_id") int client_need_id)
    {
    	servicesManager.Unassign(id,  client_need_id);
    }
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/{id}")
    public void Update(@PathVariable("id") int id, @RequestBody ServiceOffering serviceOffering)
    {
    	serviceOffering.id = id;
    	servicesManager.Update(serviceOffering);
    }
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void Create(@RequestBody ServiceOffering serviceOffering)
    {
    	servicesManager.Create(serviceOffering);
    }
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void Delete(@PathVariable("id") int id)
    {
    	servicesManager.Delete(id);
    }
}
