package svc.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import svc.data.clients.ClientDAO;
import svc.data.shelters.ShelterDAO;
import svc.data.users.UserDAO;
import svc.location.LatLng;
import svc.models.Client;
import svc.models.Gender;
import svc.models.Shelter;
import svc.models.ShelterBedAssignment;
import svc.models.User;

@Component
public class TwilioManager {
	//(480) 405-0882

	@Inject
	UserDAO userDAO;
	
	@Inject
	ShelterDAO shelterDAO;
	
	@Inject
	ClientDAO clientDAO;
	
	public String getResponse(String from, String message)
	{
		User user = userDAO.getUserByPhone(from);
		if("joke".equalsIgnoreCase(message))
		{
			return "New phone, who dis?";
		}
		
		if("bed".equalsIgnoreCase(message))
		{
			Client client =  clientDAO.getClientById(user.client_id);
			Shelter shelter = findShelter(client);
			
			if(shelter == null)
			{
				return "We're unable to locate an open bed, but Larry Rice never turns someone away.";
			}
			if(user == null)
			{
				return "Limited beds available at " + shelter.name + ".";
			}
			else
			{
				if(AssignBed(client, shelter))
				{
					return "We've reserved you a bed at " + shelter.name + " for tonight.";
				}
				else
				{
					return "We're unable to locate an open bed, but Larry Rice never turns someone away.";
				}
			}
		}
		
		return "Text BED to reserve a bed.  HELP for help.";
	}
	
	private boolean AssignBed(Client client, Shelter shelter)
	{
		List<ShelterBedAssignment> bedAssignments = shelterDAO.getBedAssignmentsForShelter(shelter.id);
		for(ShelterBedAssignment assignment : bedAssignments)
		{
			if(assignment.assigned_to_client_id == 0)
			{
				assignment.assigned_to_client_id = client.id;
				shelterDAO.assignBed(assignment);
				return true;
			}
		}
		return false;
	}
	
	private Shelter findShelter(Client client)
	{
		if(client == null)
		{
			return null;
		}
		
		for(Shelter shelter: shelterDAO.getAllShelters())
		{
			long bedCount = shelterDAO.getAvailableBedCount(shelter.id);
			if(bedCount == 0)
			{
				continue;
			}
			
			if(client.gender == Gender.FEMALE)
			{
				if(shelter.allows_women == 1)
				{
					return shelter;
				}
			}
			if(client.gender == Gender.MALE)
			{
				if(shelter.allows_men == 1)
				{
					return shelter;
				}
			}
			
			if(shelter.allows_children == 1 && shelter.allows_men == 1 && shelter.allows_women == 1)
			{
				return shelter;
			}
		}
		
		return null;
	}
}
