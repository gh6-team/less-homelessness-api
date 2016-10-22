DROP SCHEMA PUBLIC CASCADE
/*http://hsqldb.org/doc/guide/compatibility-chapt.html#coc_compatibility_mysql*/

CREATE TABLE clients (
    client_id INTEGER DEFAULT 0 NOT NULL,
    first_name varchar(50) NULL,
    middle_name varchar(50) NULL,
    last_name varchar(50) NULL,
    name_data_quality varchar(50) NULL,
    ssn INTEGER NULL,
    ssn_data_quality INTEGER NULL,
    dob date NULL,
    dob_data_quality INTEGER NULL,
    am_ind_ak_native INTEGER NULL,
    asian INTEGER NULL,
    black INTEGER NULL,
    native_hi_other_pacific INTEGER NULL,
    white INTEGER NULL,
    race_none INTEGER NULL,
    gender INTEGER NULL,
    other_gender INTEGER NULL,
    veteran_status INTEGER NULL,
    year_entered_service INTEGER NULL,
    year_separated INTEGER NULL,
    world_war_ii INTEGER NULL,
    korean_war INTEGER NULL,
    vietnam_war INTEGER NULL,
    desert_storm INTEGER NULL,
    afghanistan_oef INTEGER NULL,
    iraq_oif INTEGER NULL,
    iraq_ond INTEGER NULL,
    other_theater INTEGER NULL,
    military_branch INTEGER NULL,
    discharge_status INTEGER NULL,
    date_created date NULL,
    date_updated date NULL,
    user_id INTEGER NULL,
    primary key (client_id, date_updated)
);

CREATE TABLE disabilities (
    disabilities_id varchar(50) NOT NULL,
    project_entry_id INTEGER NULL,
    personal_id INTEGER NULL,
	information_date date NULL,
	disabilitiy_type INTEGER NULL,
	disability_response INTEGER NULL,
	indefinite_and_impairs INTEGER NULL,
	documentation_on_file INTEGER NULL,
	receiving_services INTEGER NULL,
	path_how_confirmed INTEGER NULL,
	path_smi_information INTEGER NULL,
	tcell_count_available INTEGER NULL,
	tcell_count INTEGER NULL,
	tcell_source INTEGER NULL,
	viral_load_available INTEGER NULL,
	viral_load INTEGER NULL,
	viral_load_source INTEGER NULL,
	data_collection_stage INTEGER NULL,
	date_created date NULL,
	date_updated date NULL,
	user_id INTEGER NULL,
	date_deleted date NULL,
	export_id INTEGER NULL,
	primary key (disabilities_id, date_updated)
);

CREATE TABLE employment_education (
    employment_education_id INTEGER NOT NULL,
    project_entry_id INTEGER NOT NULL,
    personal_id INTEGER NOT NULL,
	information_date date NOT NULL,
	last_grade_completed INTEGER NULL,
	school_status INTEGER NULL,
	employed INTEGER NULL,
	employment_type INTEGER NULL,
	not_employed_reason INTEGER NULL,
	data_collection_stage INTEGER NULL,
	date_created date NULL,
	date_updated date NULL,
	user_id INTEGER NULL,
	date_deleted date NULL,
	export_id INTEGER NULL,
	primary key (employment_education_id, date_updated)
);

CREATE TABLE enrollment (
    project_entry_id INTEGER NOT NULL,
    personal_id INTEGER NOT NULL,
    project_id INTEGER NOT NULL,
	entry_date date NULL,
	household_id INTEGER NULL,
	relationship_to_hoh INTEGER NULL,
	residence_prior INTEGER NULL,
	other_residence_prior INTEGER NULL,
	residence_prior_length_of_stay INTEGER NULL,
	disabling_condition INTEGER NULL,
	entry_from_street_essh INTEGER NULL,
	date_to_street_essh date NULL,
	times_homeless_past_three_years INTEGER NULL,
	months_homeless_past_three_years INTEGER NULL,
	housing_status INTEGER NULL,
	date_of_engagement date NULL,
	in_permanent_housing INTEGER NULL,
	residential_move_in_date date NULL,
	date_of_path_status date NULL,
	client_enrolled_in_path INTEGER NULL,
	reason_not_enrolled varchar(50) NULL,
	worst_housing_situation INTEGER NULL,
	percent_AMI INTEGER NULL,
	last_permanent_street varchar(50) NULL,
	last_permanent_city varchar(50) NULL,
	last_permanent_state varchar(50) NULL,
	last_permanent_zip INTEGER NULL,
	address_data_quality INTEGER NULL,
	date_of_bcp_status INTEGER NULL,
	fysb_youth INTEGER NULL,
	reason_no_servies INTEGER NULL,
	sexual_orientation INTEGER NULL,
	former_ward_child_welfare INTEGER NULL,
	child_welfare_years INTEGER NULL,
	child_welfare_months INTEGER NULL,
	former_ward_juvenile_justice INTEGER NULL,
	juvenile_justice_years INTEGER NULL,
	household_dynamics INTEGER NULL,
	sexual_orientation_gender_id_youth INTEGER NULL,
	sexual_orientation_gender_id_fam INTEGER NULL,
	housing_issues_youth INTEGER NULL,
	housing_issues_fam INTEGER NULL,
	school_educational_issues_youth INTEGER NULL,
	school_education_issues_fam INTEGER NULL,
	unemployment_youth INTEGER NULL,
	unemployment_fam INTEGER NULL,
	mental_health_issues_youth INTEGER NULL,
	mental_health_issues_fam INTEGER NULL,
	health_issues_youth INTEGER NULL,
	health_issues_fam INTEGER NULL,
	physical_disability_youth INTEGER NULL,
	physical_disability_fam INTEGER NULL,
	mental_disability_youth INTEGER NULL,
	mental_disability_fam INTEGER NULL,
	abuse_and_neglect_youth INTEGER NULL,
	abuse_and_neglect_fam INTEGER NULL,
	alcohol_drug_abuse_youth INTEGER NULL,
	alcohol_drug_abuse_fam INTEGER NULL,
	insufficient_income INTEGER NULL,
	active_military_parent INTEGER NULL,
	incarcerated_parent INTEGER NULL,
	incarcerated_parent_status INTEGER NULL,
	referral_source INTEGER NULL,
	court_ourtreach_referral_approaches INTEGER NULL,
	exchange_for_sex INTEGER NULL,
	exchange_for_sex_past_three_months INTEGER NULL,
	count_of_exchanges_for_sex INTEGER NULL,
	asked_or_forced_to_exchange_for_sex INTEGER NULL,
	asked_or_forced_to_exchange_for_sex_past_three_months INTEGER NULL,
	workplace_violence_threats INTEGER NULL,
	workplace_promise_difference INTEGER NULL,
	coerced_to_continue_work INTEGER NULL,
	labor_exploit_past_three_months INTEGER NULL,
	hp_screening_score INTEGER NULL,
	vamc_station INTEGER NULL,
	date_created date NULL,
	date_updated date NULL,
	user_id INTEGER NULL,
	date_deleted date NULL,
	export_id INTEGER NULL,
	primary key (project_entry_id, date_updated)
);

CREATE TABLE exit (
    exit_id INTEGER NOT NULL,
    project_entry_id INTEGER NOT NULL,
    personal_id INTEGER NOT NULL,
	exit_date date NOT NULL,
	destination INTEGER NULL,
	other_destination INTEGER NULL,
	assessment_disposition INTEGER NULL,
	other_disposition INTEGER NULL,
	housing_assignment INTEGER NULL,
	subsity_information INTEGER NULL,
	connection_with_soar INTEGER NULL,
	written_aftercare_plan INTEGER NULL,
	assistance_mainstream_benefits INTEGER NULL,
	permanent_housing_placement INTEGER NULL,
	temporary_shelter_placement INTEGER NULL,
	exit_counseling INTEGER NULL,
	further_follow_up_services INTEGER NULL,
	scheduled_follow_up_contacts INTEGER NULL,
	resource_package INTEGER NULL,
	other_aftercare_plan_or_action INTEGER NULL,
	project_completion_status INTEGER NULL,
	early_exit_reason INTEGER NULL,
	family_reunification_achieved INTEGER NULL,
	date_created date NULL,
	date_updated date NULL,
	user_id INTEGER NULL,
	date_deleted date NULL,
	export_id INTEGER NULL,
	primary key (exit_id, date_updated)
);

CREATE TABLE funder (
    funder_id integer NULL,
    project_id integer NULL,
    funder integer NULL,
    grant_id integer NULL,
    start_date date NULL,
    end_date date NULL,
    date_created date NOT NULL,
    date_updated date NOT NULL,
    user_id integer NULL,
    date_deleted date NULL,
    export_id integer NULL,
    primary key (funder_id, date_updated)
);

CREATE TABLE health_and_dvid (
    health_and_dvid integer NULL,
    project_entry_id integer NULL,
    personal_id integer NULL,
    information_date date NULL,
    domestic_violence_victim integer NULL,
    when_occurred integer NULL,
    currently_fleeing integer NULL,
    general_health_status integer NULL,
    dental_health_status integer NULL,
    mental_health_status integer NULL,
    pregnancy_status integer NULL,
    due_date date NULL,
    data_collection_stage integer NULL,
    date_created date NOT NULL,
    date_updated date NOT NULL,
    user_id integer NULL,
    date_deleted date NULL,
    export_id integer NULL,
    primary key (health_and_dvid, date_updated)
);

CREATE TABLE income_benefits (
    income_benefits_id integer NULL,
    project_entry_id integer NULL,
    personal_id integer NULL,
    information_date date NULL,
    income_from_any_source integer NULL,
    total_monthly_income decimal NULL,
    earned integer NULL,
    earned_amount decimal NULL,
    unemployment integer NULL,
    unemployment_amount decimal NULL,
    ssi integer NULL,
    ssi_amount decimal NULL,
    ssdi integer NULL,
    ssdi_amount decimal NULL,
    va_disability_service integer NULL,
    va_disability_service_amount decimal NULL,
    va_disability_non_service integer NULL,
    va_disability_non_service_amount decimal NULL,
    private_disability integer NULL,
    private_disability_amount decimal NULL,
    workers_comp integer NULL,
    workers_comp_amount decimal NULL,
    tanf integer NULL,
    tanf_amount decimal NULL,
    ga integer NULL,
    ga_amount decimal NULL,
    soc_sec_retirement integer NULL,
    soc_sec_retirement_amount decimal NULL,
    pension integer NULL,
    pension_amount decimal NULL,
    child_support integer NULL,
    child_support_amount decimal NULL,
    alimony integer NULL,
    alimony_amount decimal NULL,
    other_income_source integer NULL,
    other_income_amount decimal NULL,
    other_income_source_identify varchar(50) NULL,
    benefits_from_any_source integer NULL,
    snap integer NULL,
    wic integer NULL,
    tanf_child_care integer NULL,
    tanf_transportation integer NULL,
    other_tanf integer NULL,
    rental_assistance_ongoing integer NULL,
    rental_assistance_temp integer NULL,
    other_benefits_source integer NULL,
    other_benefits_source_identify varchar(50) NULL,
    insurance_from_any_source integer NULL,
    medicaid integer NULL,
    no_medicaid_reason varchar(50) NULL,
    medicare integer NULL,
    no_medicare_reason varchar(50) NULL,
    schip integer NULL,
    no_schip_reason varchar(50) NULL,
    va_medical_services integer NULL,
    no_va_med_reason varchar(50) NULL,
    employer_provided integer NULL,
    no_employer_provided_reason varchar(50) NULL,
    cobra integer NULL,
    no_cobra_reason varchar(50) NULL,
    private_pay integer NULL,
    no_private_pay_reason varchar(50) NULL,
    state_health_ins integer NULL,
    no_state_health_ins_reason varchar(50) NULL,
    hivaids_assistance integer NULL,
    no_hivaids_assistance_reason varchar(50) NULL,
    adap integer NULL,
    no_adap_reason varchar(50) NULL,
    data_collection_stage integer NULL,
    date_created date NOT NULL,
    date_updated date NOT NULL,
    user_id integer NULL,
    date_deleted date NULL,
    export_id integer NULL,
    primary key (income_benefits_id, date_updated)
);

CREATE TABLE services (
    services_id integer NULL,
    project_entry_id integer NULL,
    personal_id integer NULL,
    date_provided date NULL,
    record_type integer NULL,
    type_provided integer NULL,
    other_type_provided integer NULL,
    sub_type_provided integer NULL,
    fa_amount integer NULL,
    referral_outcome integer NULL,
    date_created date NOT NULL,
    date_updated date NOT NULL,
    user_id integer NULL,
    date_deleted date NULL,
    export_id integer NULL,
    primary key (services_id, date_updated)
);

CREATE TABLE shelters (
    id integer DEFAULT 0 NOT NULL,
    name varchar(50) NOT NULL,
    address varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    state varchar(50) NOT NULL,
    zip_code varchar(50) NOT NULL,
    phone_number varchar(50) NOT NULL,
    latitude Decimal(9,6) NULL,
    longitude Decimal(9,6) NULL,
    allows_men integer NOT NULL,
    allows_women integer NOT NULL,
    allows_children integer NOT NULL
);

CREATE TABLE shelter_beds (
    id integer DEFAULT 0 NOT NULL,
    shelter_id integer NOT NULL,
    bed_name varchar(50) NOT NULL
);

CREATE TABLE shelter_bed_assignments (
    id integer DEFAULT 0 NOT NULL,
    shelter_bed_id integer NOT NULL,
    assigned_to_client_id integer NOT NULL,
    assignment_date date NOT NULL,
    assigned_by integer NOT NULL
);





