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
import svc.models.ClientDisabilities;
import svc.models.EmploymentEducation;
import svc.models.Enrollment;

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
	
	private class ClientDiabilitiesSQLMapper implements RowMapper<ClientDisabilities> {
		public ClientDisabilities mapRow(ResultSet rs, int i) {
			ClientDisabilities clientDisabilities = new ClientDisabilities();
			try {	
				clientDisabilities.id = rs.getString("disabilities_id");
				clientDisabilities.project_entry_id = rs.getInt("project_entry_id");
				clientDisabilities.personal_id = rs.getInt("personal_id");
				clientDisabilities.information_date = rs.getDate("information_date");
				clientDisabilities.disability_type = rs.getInt("disability_type");
				clientDisabilities.disability_response = rs.getInt("disabilitiy_response");
				clientDisabilities.indefinite_and_impairs = rs.getInt("indefinite_and_impairs");
				clientDisabilities.documentation_on_file = rs.getInt("documentation_on_file");
				clientDisabilities.receiving_services = rs.getInt("receiving_services");
				clientDisabilities.path_how_confirmed = rs.getInt("path_how_confirmed");
				clientDisabilities.path_smi_information = rs.getInt("path_smi_information");
				clientDisabilities.is_tcell_count_available = rs.getBoolean("tcell_count_available");
				clientDisabilities.tcell_count = rs.getInt("tcell_count");
				clientDisabilities.tcell_source = rs.getInt("tcell_source");
				clientDisabilities.is_viral_load_available = rs.getBoolean("viral_load_availalbe");
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
				employmentEducation.id = rs.getInt("employement_education_id");
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
				enrollment.id = rs.getInt("project_entry_id");
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
	
}
