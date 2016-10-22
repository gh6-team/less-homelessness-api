package svc.managers;

import org.springframework.stereotype.Component;

@Component
public class TwilioManager {
	//(480) 405-0882
	
	public String getResponse(String from, String message)
	{
		if("bed".equalsIgnoreCase(message))
		{
			return "Bed";
		}
		return "No bed";
	}
}
