package svc.models;

import java.util.Date;
import java.util.List;

public class Client {
	public int id;
	public String first_name;
	public String middle_name;
	public String last_name;
	public int name_data_quality;
	public int ssn;
	public int ssn_data_quality;
	public Date date_of_birth;
	public int date_of_birth_quality;
	public boolean is_american_indian;
	public boolean is_asian;
	public boolean is_black;
	public boolean is_pacific_islander;
	public boolean is_white;
	public boolean has_no_race_data;
	public Gender gender;
	public VeteranStatus veteran_status;
	public int year_entered_service;
	public int year_left_service;
	public boolean is_ww2_vet;
	public boolean is_korean_war_vet;
	public boolean is_vietnam_war_vet;
	public boolean is_desert_storm_vet;
	public boolean is_afghanistan_oef_vet;
	public boolean is_iraq_oif_vet;
	public boolean is_iraq_ond_vet;
	public boolean is_other_theater_vet;
	public int military_branch;
	public int discharge_status;
	public Date date_created;
	public Date date_updated;
	public int user_id;
	
	public List<ClientDisabilities> disabilities;
	public EmploymentEducation employment_education_details;
}
