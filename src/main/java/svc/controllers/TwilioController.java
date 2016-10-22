package svc.controllers;

import java.util.Enumeration;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.ModelAttribute;
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

import svc.logging.LogSystem;
import svc.managers.TwilioManager;

@RestController
@EnableAutoConfiguration
@RequestMapping("/twilio")
public class TwilioController {
	@Inject
	TwilioManager twilioManager;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	MessagingResponse Inbound(@RequestParam("From") String from, @RequestBody String body) throws TwiMLException {
		String message = twilioManager.getResponse(from, body);
		Message twilioMessage = new Message.Builder().body(new Body(message)).build();
		MessagingResponse twilioResponse = new MessagingResponse.Builder().message(twilioMessage).build();
		return twilioResponse;
	}

	@ModelAttribute
	protected void logging(HttpServletRequest request, HttpServletResponse response) {
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			LogSystem.LogEvent("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
		}
		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramName = (String) params.nextElement();
			LogSystem.LogEvent("Parameter Name - " + paramName + ", Value - " + request.getParameter(paramName));
		}
	}
}
