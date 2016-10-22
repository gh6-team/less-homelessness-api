package svc.models;

import java.util.Date;

public class Enrollment {
	public int id;
	public int project_entry_id;
	public int personal_id;
	public int project_id;
	public Date entry_date;
	public int household_id;
	public int relationship_to_hoh;
	public Integer prior_residence;
	public String other_residence_prior;
	public Integer prior_residence_length_of_stay;
	public Boolean has_disabling_condition;
	public Integer entry_from_street_essh;
	public Date date_to_street_essh;
	public Integer times_homeless_last_three_years;
	public Integer months_homeless_last_three_years;
	public Integer housing_status;
	public Date date_of_engagement;
	public Boolean in_permanent_housing;
	public Date residential_move_in_date;
	public Date date_of_path_status;
	public Integer client_enrolled_in_path;
	public String reason_not_enrolled;
	public Integer worst_housing_situation;
	public Integer percent_ami;
	public String last_permanent_street;
	public String last_permanent_city;
	public String last_permanent_state;
	public Integer last_permanent_zip;
	public Integer address_data_quality;
	public Date date_of_bcp_status;
	public Integer hp_screening_score;
	public Integer vamc_station;
	public Date date_created;
	public Date date_updated;
	public int user_id;
	public Date date_deleted;
	public int export_id;
}
