package svc.controllers;

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

@RestController
@EnableAutoConfiguration
@RequestMapping("/twilio")
public class TwilioController {

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	String Inbound(@RequestParam("From") String from, @RequestBody String body)
	{
		Message twilioMessage = new Message.Builder().body(new Body("Message from: " + from)).build();
		MessagingResponse twilioResponse = new MessagingResponse.Builder().message(twilioMessage).build();
		return twilioResponse.toString();
	}
}
