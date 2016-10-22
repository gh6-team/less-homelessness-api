package svc.managers;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import svc.data.users.UserDAO;
import svc.models.User;

@Component
public class TwilioManager {
	//(480) 405-0882

	@Inject
	UserDAO userDAO;
	
	public String getResponse(String from, String message)
	{
		User user = userDAO.getUserByPhone(from);
		if("bed".equalsIgnoreCase(message))
		{
			if(user == null)
			{
				return "Limited beds available at St. Patrick's Center.";
			}
			else
			{
				return "We've reserved you a bed at St. Patrick's Center for tonight.";
			}
		}
		return "Text BED to reserve a bed.  HELP for help.";
	}
}
