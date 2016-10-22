package svc.controllers;

import javax.inject.Inject;
import javax.security.sasl.AuthenticationException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import svc.dto.Credentials;
import svc.managers.UserManager;
import svc.models.User;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

	@Inject
	UserManager userManager;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/login")
	public User Login(@RequestBody Credentials credentials) throws AuthenticationException {
		User user = userManager.Login(credentials.userId, credentials.password);
		if (user == null) {
			throw new AuthenticationException();
		}
		
		return user;
	}
}
