package svc.data.clients;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import svc.data.jdbc.BaseJdbcDao;
import svc.logging.LogSystem;
import svc.models.*;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ClientDAO extends BaseJdbcDao {
	private SimpleJdbcInsert clientInsert;
	private SimpleJdbcInsert clientInsertExisting;
	private SimpleJdbcInsert clientDisabilitiesInsert;
	private SimpleJdbcInsert employmentEducationInsert;

    @Override
	protected void createSimpleJdbcInserts(DataSource dataSource) {
    	employmentEducationInsert = new SimpleJdbcInsert(dataSource)
    			.withTableName("employment_education")
    			.usingGeneratedKeyColumns("employment_education_id")
    			.usingColumns("project_entry_id",
    					"personal_id",
    					"information_date",
    					"last_grade_completed",
    					"school_status",
    					"employed",
    					"employment_type",
    					"not_employed_reason",
    					"data_collection_stage",
    					"date_created",
    					"date_updated",
    					"user_id",
    					"date_deleted",
    					"export_id");
    	clientDisabilitiesInsert = new SimpleJdbcInsert(dataSource)
    			.withTableName("disabilities")
    			.usingGeneratedKeyColumns("disabilities_id")
    			.usingColumns("project_entry_id",
    					"personal_id",
    					"information_date",
    					"disabilitiy_type",
    					"disability_response",
    					"indefinite_and_impairs",
    					"documentation_on_file",
    					"receiving_services",
    					"path_how_confirmed",
    					"path_smi_information",
    					"tcell_count_available",
    					"tcell_count",
    					"tcell_source",
    					"viral_load_available",
    					"viral_load",
    					"viral_load_source",
    					"data_collection_stage",
    					"date_created",
    					"date_updated",
    					"user_id",
    					"date_deleted",
    					"export_id"
    					);
    	clientInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("clients")
                .usingGeneratedKeyColumns("client_id")
                .usingColumns(
					"first_name", 
					"middle_name", 
					"last_name", 
					"name_data_quality",
					"ssn",
					"ssn_data_quality",
					"dob",
					"dob_data_quality",
					"am_ind_ak_native",
					"asian",
					"black",
					"native_hi_other_pacific",
					"race_none",
					"gender",
					"other_gender",
					"veteran_status",
					"year_entered_service",
					"year_separated",
					"world_war_ii",
					"korean_war",
					"vietnam_war",
					"desert_storm",
					"afghanistan_oef",
					"iraq_oif",
					"iraq_ond",
					"other_theater",
					"military_branch",
					"discharge_status",
					"date_created",
					"date_updated",
					"user_id");
    	
    	clientInsertExisting = new SimpleJdbcInsert(dataSource)
                .withTableName("clients")
                .usingColumns(
                		"client_id", 
                		"first_name", 
                		"middle_name", 
                		"last_name", 
                		"name_data_quality",
                		"ssn",
                		"ssn_data_quality",
                		"dob",
                		"dob_data_quality",
                		"am_ind_ak_native",
                		"asian",
                		"black",
                		"native_hi_other_pacific",
                		"race_none",
                		"gender",
                		"other_gender",
                		"veteran_status",
                		"year_entered_service",
                		"year_separated",
                		"world_war_ii",
                		"korean_war",
                		"vietnam_war",
                		"desert_storm",
                		"afghanistan_oef",
                		"iraq_oif",
                		"iraq_ond",
                		"other_theater",
                		"military_branch",
                		"discharge_status",
                		"date_created",
                		"date_updated",
                		"user_id");
    }
    
    public Client saveClient(Client client)
    {
		try {
			MapSqlParameterSource clientParameters = getClientParameters(client);
			if(client.id == 0)
			{
				Number key = clientInsert.executeAndReturnKey(clientParameters);
				client.id = key.intValue();
			}
			else
			{
				Map<String, Object> parameterMap = new HashMap<String, Object>();
				parameterMap.put("loginId", client.id);
				String sql = "DELETE FROM clients WHERE client_id = :loginId";
				jdbcTemplate.update(sql, parameterMap);
				
				clientParameters.addValue("client_id", client.id);
				clientInsertExisting.execute(clientParameters);
			}
			saveClientDisabilities(client);
			saveEmploymentEducation(client);
			return client;
		} catch (Exception e) {
			LogSystem.LogDBException(e);
			return null;
		}
    }
    
    private void saveEmploymentEducation(Client client)
    {

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("loginId", client.id);
		String sql = "DELETE FROM employment_education WHERE personal_id = :loginId";
		jdbcTemplate.update(sql, parameterMap);
		
	    EmploymentEducation employmentEducation = client.employment_education_details;
	    employmentEducation.personal_id = client.id;
	    MapSqlParameterSource employmentParameters = getEmploymentParameters(employmentEducation);
	    Number key = employmentEducationInsert.executeAndReturnKey(employmentParameters);
	    employmentEducation.id = key.intValue();
    }
    
    private void saveClientDisabilities(Client client)
    {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("loginId", client.id);
		String sql = "DELETE FROM disabilities WHERE personal_id = :loginId";
		jdbcTemplate.update(sql, parameterMap);
		
    	for(ClientDisabilities disability : client.disabilities)
    	{
    		disability.personal_id = client.id;
			MapSqlParameterSource disabilityParameters = getDisabilityParameters(disability);
			clientDisabilitiesInsert.executeAndReturnKey(disabilityParameters);
    	}
    }

    private MapSqlParameterSource getEmploymentParameters(EmploymentEducation employment)
    {
    	return new MapSqlParameterSource()
    			.addValue("employement_education_id", employment.id)
    			.addValue("project_entry_id", employment.project_entry_id)
    			.addValue("personal_id", employment.personal_id)
    			.addValue("information_date", employment.information_date)
    			.addValue("last_grade_completed", employment.last_grade_completed)
    			.addValue("school_status", employment.school_status)
    			.addValue("employed", employment.employed_status)
    			.addValue("employment_type", employment.employment_type)
    			.addValue("not_employed_reason", employment.not_employed_reason)
    			.addValue("data_collection_stage", employment.data_collection_stage)
    			.addValue("date_created", employment.date_created)
    			.addValue("date_updated", employment.date_updated)
    			.addValue("user_id", employment.user_id)
    			.addValue("date_deleted", employment.date_deleted)
    			.addValue("export_id", employment.export_id);
    }
    private MapSqlParameterSource getDisabilityParameters(ClientDisabilities disability)
    {
    	return new MapSqlParameterSource()
    			.addValue("project_entry_id", disability.project_entry_id)
    			.addValue("personal_id", disability.personal_id)
    			.addValue("information_date", disability.information_date)
    			.addValue("disabilitiy_type", disability.disability_type)
    			.addValue("disability_response", disability.disability_response)
    			.addValue("indefinite_and_impairs", disability.indefinite_and_impairs)
    			.addValue("documentation_on_file", disability.documentation_on_file)
    			.addValue("receiving_services", disability.receiving_services)
    			.addValue("path_how_confirmed", disability.path_how_confirmed)
    			.addValue("path_smi_information", disability.path_smi_information)
    			.addValue("tcell_count_available", disability.is_tcell_count_available)
    			.addValue("tcell_count", disability.tcell_count)
    			.addValue("tcell_source", disability.tcell_source)
    			.addValue("viral_load_available", disability.is_viral_load_available)
    			.addValue("viral_load", disability.viral_load)
    			.addValue("viral_load_source", disability.viral_load_source)
    			.addValue("data_collection_stage", disability.data_collection_stage)
    			.addValue("date_created", disability.date_created)
    			.addValue("date_updated", disability.date_updated)
    			.addValue("user_id", disability.user_id)
    			.addValue("date_deleted", disability.date_deleted)
    			.addValue("export_id", disability.export_id);

    }
    
	private MapSqlParameterSource getClientParameters(Client client) {
		return new MapSqlParameterSource()
		        .addValue("first_name", client.first_name)
		        .addValue("middle_name", client.middle_name)
		        .addValue("last_name", client.last_name)
		        .addValue("name_data_quality", client.name_data_quality)
		        .addValue("ssn", client.ssn)
		        .addValue("ssn_data_quality", client.ssn_data_quality)
		        .addValue("dob", client.date_of_birth)
		        .addValue("dob_data_quality", client.date_of_birth_quality)
		        .addValue("am_indian_ak_native", client.is_american_indian ? 1 : 0)
		        .addValue("asian", client.is_asian ? 1 : 0)
		        .addValue("black", client.is_black ? 1 : 0)
		        .addValue("native_hi_other_pacific", client.is_pacific_islander ? 1 : 0)
		        .addValue("race_none", client.has_no_race_data ? 1 : 0)
		        .addValue("gender", client.gender == null ? null : client.gender.getGender())
		        .addValue("other_gender", client.gender == null ? null : client.gender.getOtherGender())
		        .addValue("veteran_status", client.veteran_status == null ? null : client.veteran_status.toColumnValue())
		        .addValue("year_entered_service", client.year_entered_service)
		        .addValue("year_separated", client.year_left_service)
		        .addValue("world_war_ii", client.is_ww2_vet ? 1 : 0)
		        .addValue("korean_war", client.is_korean_war_vet ? 1 : 0)
		        .addValue("vietnam_war", client.is_vietnam_war_vet ? 1 : 0)
		        .addValue("desert_storm", client.is_desert_storm_vet ? 1 : 0)
		        .addValue("afghanistan_oef", client.is_afghanistan_oef_vet ? 1 : 0)
		        .addValue("iraq_oif", client.is_iraq_oif_vet ? 1 : 0)
		        .addValue("iraq_ond", client.is_iraq_ond_vet ? 1 : 0)
		        .addValue("other_theater", client.is_other_theater_vet ? 1 : 0)
		        .addValue("military_branch", client.military_branch)
		        .addValue("discharge_status", client.discharge_status)
		        .addValue("date_created", client.date_created)
		        .addValue("date_updated", new Date())
		        .addValue("user_id", client.user_id);
	}

    public Client getClientById(int client_id) {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("client_id", client_id);
            String sql = "SELECT * FROM clients WHERE client_id = :client_id";
            Client client = jdbcTemplate.queryForObject(sql, parameterMap, new ClientSQLMapper());
            String disabilitySql = "SELECT * FROM disabilities WHERE personal_id = :client_id";
            client.disabilities = jdbcTemplate.query(disabilitySql, parameterMap, new ClientDisabilitiesSQLMapper());
            client.employment_education_details = getEmploymentEducation(client_id);
            return client;
        } catch (Exception e) {
            LogSystem.LogDBException(e);
            return null;
        }
    }
    
    public EmploymentEducation getEmploymentEducation(int client_id)
    {
    	try
    	{
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("client_id", client_id);
	    	String employmentSql = "SELECT * FROM employment_education WHERE personal_id = :client_id";
	        return jdbcTemplate.queryForObject(employmentSql, parameterMap, new EmploymentEducationSQLMapper());
    	}catch(Exception e){
    		LogSystem.LogDBException(e);
    		return new EmploymentEducation();
    	}
    }

    private class ClientSQLMapper implements RowMapper<Client> {
        public Client mapRow(ResultSet rs, int i) {
			Client client = new Client();
			try {	
				client.id = rs.getInt("client_id");
				client.first_name = rs.getString("first_name");
				client.middle_name = rs.getString("middle_name");
				client.last_name = rs.getString("last_name");
				client.name_data_quality = rs.getInt("name_data_quality");
				client.ssn = rs.getInt("ssn");
				client.ssn_data_quality = rs.getInt("ssn_data_quality");
				client.date_of_birth = rs.getDate("dob");
				client.date_of_birth_quality = rs.getInt("dob_data_quality");
                client.is_american_indian = rs.getBoolean("am_ind_ak_native");
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
	
	private class ClientDisabilitiesSQLMapper implements RowMapper<ClientDisabilities> {
		public ClientDisabilities mapRow(ResultSet rs, int i) {
			ClientDisabilities clientDisabilities = new ClientDisabilities();
			try {	
				clientDisabilities.id = rs.getString("disabilities_id");
				clientDisabilities.project_entry_id = rs.getInt("project_entry_id");
				clientDisabilities.personal_id = rs.getInt("personal_id");
				clientDisabilities.information_date = rs.getDate("information_date");
				clientDisabilities.disability_type = rs.getInt("disabilitiy_type");
				clientDisabilities.disability_response = rs.getInt("disability_response");
				clientDisabilities.indefinite_and_impairs = rs.getInt("indefinite_and_impairs");
				clientDisabilities.documentation_on_file = rs.getInt("documentation_on_file");
				clientDisabilities.receiving_services = rs.getInt("receiving_services");
				clientDisabilities.path_how_confirmed = rs.getInt("path_how_confirmed");
				clientDisabilities.path_smi_information = rs.getInt("path_smi_information");
				clientDisabilities.is_tcell_count_available = rs.getBoolean("tcell_count_available");
				clientDisabilities.tcell_count = rs.getInt("tcell_count");
				clientDisabilities.tcell_source = rs.getInt("tcell_source");
				clientDisabilities.is_viral_load_available = rs.getBoolean("viral_load_available");
				clientDisabilities.viral_load = rs.getInt("viral_load");
				clientDisabilities.viral_load_source = rs.getInt("viral_load_source");
				clientDisabilities.data_collection_stage = rs.getInt("data_collection_stage");
				clientDisabilities.date_created = rs.getDate("date_created");
				clientDisabilities.date_updated = rs.getDate("date_updated");
				clientDisabilities.user_id = rs.getInt("user_id");
				clientDisabilities.date_deleted = rs.getDate("date_deleted");
				clientDisabilities.export_id = rs.getInt("export_id");
				
			} catch (Exception e) {
				LogSystem.LogDBException(e);
				return null;
			}
			
			return clientDisabilities;
		}
	}
	
	private class EmploymentEducationSQLMapper implements RowMapper<EmploymentEducation> {
		public EmploymentEducation mapRow(ResultSet rs, int i) {
			EmploymentEducation employmentEducation = new EmploymentEducation();
			try {	
				employmentEducation.id = rs.getInt("employment_education_id");
				employmentEducation.project_entry_id = rs.getInt("project_entry_id");
				employmentEducation.personal_id = rs.getInt("personal_id");
				employmentEducation.information_date = rs.getDate("information_date");
				employmentEducation.last_grade_completed = rs.getInt("last_grade_completed");
				employmentEducation.school_status = rs.getInt("school_status");
				employmentEducation.employed_status = rs.getInt("employed");
				employmentEducation.employment_type = rs.getInt("employment_type");
				employmentEducation.not_employed_reason = rs.getInt("not_employed_reason");
				employmentEducation.data_collection_stage = rs.getInt("data_collection_stage");
				employmentEducation.date_created = rs.getDate("date_created");
				employmentEducation.date_updated = rs.getDate("date_updated");
				employmentEducation.user_id = rs.getInt("user_id");
				employmentEducation.date_deleted = rs.getDate("date_deleted");
				employmentEducation.export_id = rs.getInt("export_id");
				
			} catch (Exception e) {
				LogSystem.LogDBException(e);
				return null;
			}
			
			return employmentEducation;
		}
	}
	
	private class EnrollmentSQLMapper implements RowMapper<Enrollment> {
		public Enrollment mapRow(ResultSet rs, int i) {
			Enrollment enrollment = new Enrollment();
			try {	
				enrollment.id = rs.getInt("enrollment_id");
				enrollment.project_entry_id = rs.getInt("project_entry_id");
				enrollment.personal_id = rs.getInt("personal_id");
				enrollment.project_id = rs.getInt("project_id");
				enrollment.entry_date = rs.getDate("entry_date");
				enrollment.household_id = rs.getInt("household_id");
				enrollment.relationship_to_hoh = rs.getInt("relationship_to_hoh");
				enrollment.prior_residence = rs.getInt("residence_prior");
				enrollment.other_residence_prior = rs.getString("other_residence_prior");
				enrollment.prior_residence_length_of_stay = rs.getInt("residence_prior_length_of_stay");
				enrollment.has_disabling_condition = rs.getBoolean("disabling_condition");
				enrollment.entry_from_street_essh = rs.getInt("entry_from_street_essh");
				enrollment.date_to_street_essh = rs.getDate("date_to_street_essh");
				enrollment.times_homeless_last_three_years = rs.getInt("times_homeless_past_three_years");
				enrollment.months_homeless_last_three_years = rs.getInt("months_homeless_last_three_years");
				enrollment.housing_status = rs.getInt("housing_status");
				enrollment.date_of_engagement = rs.getDate("date_of_engagement");
				enrollment.in_permanent_housing = rs.getBoolean("in_permanent_housing");
				enrollment.residential_move_in_date = rs.getDate("residential_move_in_date");
				enrollment.date_of_path_status = rs.getDate("date_of_path_status");
				enrollment.client_enrolled_in_path = rs.getInt("client_enrolled_in_path");
				enrollment.reason_not_enrolled = rs.getString("reason_not_enrolled");
				enrollment.worst_housing_situation = rs.getInt("worst_housing_situation");
				enrollment.percent_ami = rs.getInt("percent_ami");
				enrollment.last_permanent_street = rs.getString("last_permanent_street");
				enrollment.last_permanent_city = rs.getString("last_permanent_city");
				enrollment.last_permanent_state = rs.getString("last_permanent_state");
				enrollment.last_permanent_zip = rs.getInt("last_permanent_zip");
				enrollment.address_data_quality = rs.getInt("address_data_quality");
				enrollment.date_of_bcp_status = rs.getDate("date_of_bcp_status");
				enrollment.hp_screening_score = rs.getInt("hp_screening_score");
				enrollment.vamc_station = rs.getInt("vacm_station");
				enrollment.date_created = rs.getDate("date_created");
				enrollment.date_updated = rs.getDate("date_updated");
				enrollment.user_id = rs.getInt("user_id");
				enrollment.date_deleted = rs.getDate("date_deleted");
				enrollment.export_id = rs.getInt("export_id");
				
			} catch (Exception e) {
				LogSystem.LogDBException(e);
				return null;
			}
			
			return enrollment;
		}
	}
	
	private class ExitSQLMapper implements RowMapper<Exit> {
		public Exit mapRow(ResultSet rs, int i) {
			Exit exit = new Exit();
			try {	
				exit.id = rs.getInt("exit_id");
				exit.project_entry_id = rs.getInt("project_entry_id");
				exit.personal_id = rs.getInt("personal_id");
				exit.exit_date = rs.getDate("exit_date");
				exit.destination = rs.getInt("destination");
				exit.housing_assessment = rs.getInt("housing_assessment");
				exit.subsidy_information = rs.getInt("subsidy_information");
				exit.project_completion_status = rs.getInt("project_completion_status");
				exit.early_exit_reason = rs.getInt("early_exit_reason");
				exit.date_created = rs.getDate("date_created");
				exit.date_updated = rs.getDate("date_updated");
				exit.user_id = rs.getInt("user_id");
				exit.date_deleted = rs.getDate("date_deleted");
				exit.export_id = rs.getInt("export_id");
				
			} catch (Exception e) {
				LogSystem.LogDBException(e);
				return null;
			}
			
			return exit;
		}
	}
	
	private class FunderSQLMapper implements RowMapper<Funder> {
		public Funder mapRow(ResultSet rs, int i) {
			Funder funder = new Funder();
			try {	
				funder.id = rs.getInt("funder_id");
				funder.funder = rs.getInt("funder");
				funder.grant_id = rs.getInt("grant_id");
				funder.start_date = rs.getDate("start_date");
				funder.end_date = rs.getDate("end_date");
				funder.date_created = rs.getDate("date_created");
				funder.date_updated = rs.getDate("date_updated");
				funder.user_id = rs.getInt("user_id");
				funder.date_deleted = rs.getDate("date_deleted");
				funder.export_id = rs.getInt("export_id");
				
			} catch (Exception e) {
				LogSystem.LogDBException(e);
				return null;
			}
			
			return funder;
		}
	}
	
}
