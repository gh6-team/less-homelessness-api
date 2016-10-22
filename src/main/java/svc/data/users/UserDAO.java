package svc.data.users;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import svc.data.jdbc.BaseJdbcDao;
import svc.logging.LogSystem;
import svc.models.User;

@Repository
public class UserDAO extends BaseJdbcDao {
	public User checkUserLogin(String userId, String pwd) {
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("userId", userId);
			parameterMap.put("pwd", pwd);
			String sql = "SELECT users.* FROM users INNER JOIN user_login ON users.id = user_login.id " + 
					 "WHERE user_login.userid = :userId AND user_login.pwd = :pwd";
			return jdbcTemplate.queryForObject(sql, parameterMap, new UserSQLMapper());
		} catch (Exception e) {
			LogSystem.LogDBException(e);
			return null;
		}
	}
	

	private class UserSQLMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int i) {
			User user = new User();
			try {	
				user.id = rs.getLong("id");
				user.name = rs.getString("name");
				user.roleName = rs.getString("role_name");
				user.organizationName = rs.getString("organization_name");
				user.phone = rs.getString("phone");
			} catch (Exception e) {
				LogSystem.LogDBException(e);
				return null;
			}
			
			return user;
		}
	}
}
