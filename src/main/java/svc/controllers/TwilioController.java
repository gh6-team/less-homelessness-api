package svc.controllers;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.twiml.Body;
import com.twilio.twiml.Message;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.TwiMLException;

import svc.managers.TwilioManager;

@RestController
@EnableAutoConfiguration
@RequestMapping("/twilio")
public class TwilioController {
	@Inject
	TwilioManager twilioManager;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	MessagingResponse Inbound(@RequestParam("From") String from, @RequestBody String body) throws TwiMLException
	{
		String message = twilioManager.getResponse(from, body);
		Message twilioMessage = new Message.Builder().body(new Body(message)).build();
		MessagingResponse twilioResponse = new MessagingResponse.Builder().message(twilioMessage).build();
		return twilioResponse;
	}
}
