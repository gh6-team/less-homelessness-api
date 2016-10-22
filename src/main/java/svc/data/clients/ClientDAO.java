package svc.data.clients;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import svc.data.jdbc.BaseJdbcDao;
import svc.logging.LogSystem;
import svc.models.Client;
import svc.models.Gender;
import svc.models.VeteranStatus;

@Repository
public class ClientDAO extends BaseJdbcDao {
	private SimpleJdbcInsert clientInsert;

    @Override
	protected void createSimpleJdbcInserts(DataSource dataSource) {
    	clientInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("client")
                .usingGeneratedKeyColumns("id")
                .usingColumns("client_id", "first_name", "middle_name", "last_name", "user_id");
    	
    }
    
	
	private class ClientSQLMapper implements RowMapper<Client> {
		public Client mapRow(ResultSet rs, int i) {
			Client client = new Client();
			try {	
				client.id = rs.getInt("client_id");
				client.first_name = rs.getString("first_name");
				client.middle_name = rs.getString("middle_name");
				client.last_name = rs.getString("short_description");
				client.name_data_quality = rs.getInt("name_data_quality");
				client.ssn = rs.getInt("ssn");
				client.ssn_data_quality = rs.getInt("ssn_data_quality");
				client.date_of_birth = rs.getDate("dob");
				client.date_of_birth_quality = rs.getInt("dob_data_quality");
				client.is_american_indian = rs.getBoolean("am_indian_ak_native");
				client.is_asian = rs.getBoolean("asian");
				client.is_black = rs.getBoolean("black");
				client.is_pacific_islander = rs.getBoolean("native_hi_other_pacific");
				client.has_no_race_data = rs.getBoolean("race_none");
				client.gender = Gender.fromColumns(rs.getInt("gender"), rs.getBoolean("other_gender"));
				client.veteran_status = VeteranStatus.fromColumn(rs.getInt("veteran_status"));
				client.year_entered_service = rs.getInt("year_entered_service");
				client.year_left_service = rs.getInt("year_separated");
				client.is_ww2_vet = rs.getBoolean("world_war_ii");
				client.is_korean_war_vet = rs.getBoolean("korean_war");
				client.is_vietnam_war_vet = rs.getBoolean("vietnam_war");
				client.is_desert_storm_vet = rs.getBoolean("desert_storm");
				client.is_afghanistan_oef_vet = rs.getBoolean("afghanistan_oef");
				client.is_iraq_oif_vet = rs.getBoolean("iraq_oif");
				client.is_iraq_ond_vet = rs.getBoolean("iraq_ond");
				client.is_other_theater_vet = rs.getBoolean("other_theater");
				client.military_branch = rs.getInt("military_branch");
				client.discharge_status = rs.getInt("discharge_status");
				client.date_created = rs.getDate("date_created");
				client.date_updated = rs.getDate("date_updated");
				client.user_id = rs.getInt("user_id");
				
			} catch (Exception e) {
				LogSystem.LogDBException(e);
				return null;
			}
			
			return client;
		}
	}
	
}
