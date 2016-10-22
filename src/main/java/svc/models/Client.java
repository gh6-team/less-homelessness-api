package svc.models;

import java.util.Date;

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
	
}
