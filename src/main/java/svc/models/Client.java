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
	public Date date_updated = new Date();
	public int user_id;
	
	public int total_months_homeless;
	public int times_house_then_homeless;
	public int times_er;
	public int police_interactions;
	public int times_ambulance;
	public int times_used_crisis_service;
	public int times_hospitalized;
	public boolean attacked_since_homeless;
	public boolean threatened_harm;
	public boolean pending_legal_action;
	public boolean is_forced_or_tricked;
	public boolean risky_activity;
	public boolean is_primary_sleeping_location_shelter;
	public boolean owe_money;
	public boolean has_regular_income;
	public boolean money_covers_expenses;
	public boolean has_planned_activities;
	public boolean has_disliked_friends;
	public boolean friends_negatively_influence;
	public boolean has_poor_hygiene;
	
	public boolean has_regular_care;
	public boolean has_kidney_disease;
	public boolean frostbite_history;
	public boolean has_liver_disease;
	public boolean aids;
	
	public boolean heatstroke_history;
	public boolean heart_disease;
	public boolean emphysema;
	public boolean diabetes;
	public boolean asthma;
	public boolean cancer;
	public boolean hep_c;
	public boolean tuberculosis;
	public boolean signs_of_health_condition;
	
	public boolean problematic_drug_use;
	public boolean daily_alcohol_consumption;
	public boolean used_injection_drugs;
	public boolean returned_to_drugs;
	public boolean non_beverage_alcohol;
	public boolean blacked_out;
	public boolean signs_of_drug_alcohol_abuse;
	
	public boolean been_committed;
	public boolean er_for_mental_health;
	public boolean spoken_with_psychiatrist;
	public boolean head_trauma;
	public boolean learning_disability;
	public boolean problems_concentrating;
	public boolean signs_of_mental_illness;
	
	public boolean not_taking_medicine;
	public boolean homelessness_cause_by_abuse;
		
	public List<ClientDisabilities> disabilities;
	public EmploymentEducation employment_education_details;
}
