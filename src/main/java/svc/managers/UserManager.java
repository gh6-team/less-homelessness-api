package svc.managers;

import javax.inject.Inject;

import svc.data.users.UserDAO;
import svc.models.User;

public class UserManager {
	@Inject
	private UserDAO userDAO;
	
	public User Login(String userId, String pwd) {
		return userDAO.checkUserLogin(userId, pwd);
	}

}
