package svc.managers;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import svc.data.users.UserDAO;
import svc.models.User;

@Component
public class UserManager {
	@Inject
	private UserDAO userDAO;
	
	public User Login(String userId, String pwd) {
		return userDAO.checkUserLogin(userId, pwd);
	}
	
	public User GetUserByPhone(String phone) {
        return userDAO.getUserByPhone(phone);
    }

}
