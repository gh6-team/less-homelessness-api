package svc.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import svc.data.services.ServiceOfferingDAO;
import svc.models.ServiceOffering;

@Component
public class ServiceOfferingManager {
	@Inject
	ServiceOfferingDAO serviceOfferingDAO;

	public List<ServiceOffering> getAllServiceOfferings(){
		return serviceOfferingDAO.getAllServiceOfferings();
	}
	
	public List<ServiceOffering> getServiceOfferings(int organization_id){
		return serviceOfferingDAO.getServiceOfferings(organization_id);
	}
	
	public void Assign(int service_id, int client_id)
	{
		serviceOfferingDAO.Assign(service_id, client_id);
	}
	
	public void Unassign(int service_id, int client_id)
	{
		serviceOfferingDAO.Unassign(service_id, client_id);
	}
	
	public void Update(ServiceOffering serviceOffering)
	{
		serviceOfferingDAO.Update(serviceOffering);
	}
    
	public void Create(ServiceOffering serviceOffering)
	{
		serviceOfferingDAO.Create(serviceOffering);
	}
	
	public void Delete(int service_id)
	{
		serviceOfferingDAO.Delete(service_id);
	}
}
