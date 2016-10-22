DROP TABLE IF EXISTS citations;
CREATE TABLE citations (
    id integer DEFAULT 0 NOT NULL,
    citation_number text NULL,
    citation_date date NULL,
    first_name text NULL,
    last_name text NULL,
    date_of_birth date NULL,
    defendant_address text NULL,
    defendant_city text NULL,
    defendant_state text NULL,
    drivers_license_number text,
    court_date date NULL,
    court_location text NULL,
    court_address text NULL,
    court_id integer NULL
);

DROP TABLE IF EXISTS courts;
CREATE TABLE courts (
    id integer NOT NULL,
    latitude double precision,
    longitude double precision,
    municipality text,
    address text,
    city text,
    state text,
    zip_code text
);

DROP TABLE IF EXISTS opportunities;
CREATE TABLE opportunities (
    id integer NOT NULL,
    sponsor_id integer NOT NULL,
    name text NOT NULL,
    short_description text NOT NULL,
    full_description text,
    court_id integer NOT NULL
);

DROP TABLE IF EXISTS opportunity_need_pairings;
CREATE TABLE opportunity_need_pairings (
    opportunity_need_id integer,
    violation_id integer,
    status text,
    id integer
);

DROP TABLE IF EXISTS opportunity_needs;
CREATE TABLE opportunity_needs (
    id integer NOT NULL,
    opportunity_id integer,
    start_time DATETIME,
    end_time DATETIME,
    violation_fine_limit numeric,
    desired_count integer,
    description text
);

DROP TABLE IF EXISTS sponsor_login;
CREATE TABLE sponsor_login (
    id integer NOT NULL,
    userid text,
    pwd text
);

DROP TABLE IF EXISTS sponsors;
CREATE TABLE sponsors (
    id integer NOT NULL,
    name text,
    short_description text,
    contact_email text,
    contact_phonenumber text
);

DROP TABLE IF EXISTS violations;
CREATE TABLE violations (
    id integer DEFAULT 0 NOT NULL,
    citation_number text,
    violation_number text,
    violation_description text,
    warrant_status TINYINT(1) DEFAULT 0,
    warrant_number text,
    status text,
    status_date TIMESTAMP,
    fine_amount numeric(15,2),
    court_cost numeric(15,2)
);

DROP TABLE IF EXISTS funder;
CREATE TABLE funder (
    funder_id integer NULL,
    project_id integer NULL,
    funder integer NULL,
    grant_id integer NULL,
    start_date TIMESTAMP NULL,
    end_date TIMESTAMP NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP NOT NULL,
    user_id integer NULL,
    date_deleted TIMESTAMP NULL,
    export_id integer NULL
);

DROP TABLE IF EXISTS health_and_dvid;
CREATE TABLE health_and_dvid (
    health_and_dvid integer NULL,
    project_entry_id integer NULL,
    personal_id integer NULL,
    information_date TIMESTAMP NULL,
    domestic_violence_victim integer NULL,
    when_occurred integer NULL,
    currently_fleeing integer NULL,
    general_health_status integer NULL,
    dental_health_status integer NULL,
    mental_health_status integer NULL,
    pregnancy_status integer NULL,
    due_date TIMESTAMP NULL,
    data_collection_stage integer NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP NOT NULL,
    user_id integer NULL,
    date_deleted TIMESTAMP NULL,
    export_id integer NULL
);

DROP TABLE IF EXISTS income_benefits;
CREATE TABLE income_benefits (
    income_benefits_id integer NULL,
    project_entry_id integer NULL,
    personal_id integer NULL,
    information_date TIMESTAMP NULL,
    income_from_any_source integer NULL,
    total_monthly_income REPLACE_MONEY NULL,
    earned integer NULL,
    earned_amount REPLACE_MONEY NULL,
    unemployment integer NULL,
    unemployment_amount REPLACE_MONEY NULL,
    ssi integer NULL,
    ssi_amount REPLACE_MONEY NULL,
    ssdi integer NULL,
    ssdi_amount REPLACE_MONEY NULL,
    va_disability_service integer NULL,
    va_disability_service_amount REPLACE_MONEY NULL,
    va_disability_non_service integer NULL,
    va_disability_non_service_amount REPLACE_MONEY NULL,
    private_disability integer NULL,
    private_disability_amount REPLACE_MONEY NULL,
    workers_comp integer NULL,
    workers_comp_amount REPLACE_MONEY NULL,
    tanf integer NULL,
    tanf_amount REPLACE_MONEY NULL,
    ga integer NULL,
    ga_amount REPLACE_MONEY NULL,
    soc_sec_retirement integer NULL,
    soc_sec_retirement_amount REPLACE_MONEY NULL,
    pension integer NULL,
    pension_amount REPLACE_MONEY NULL,
    child_support integer NULL,
    child_support_amount REPLACE_MONEY NULL,
    alimony integer NULL,
    alimony_amount REPLACE_MONEY NULL,
    other_income_source integer NULL,
    other_income_amount REPLACE_MONEY NULL,
    other_income_source_identify text NULL,
    benefits_from_any_source integer NULL,
    snap integer NULL,
    wic integer NULL,
    tanf_child_care integer NULL,
    tanf_transportation integer NULL,
    other_tanf integer NULL,
    rental_assistance_ongoing integer NULL,
    rental_assistance_temp integer NULL,
    other_benefits_source integer NULL,
    other_benefits_source_identify text NULL,
    insurance_from_any_source integer NULL,
    medicaid integer NULL,
    no_medicaid_reason text NULL,
    medicare integer NULL,
    no_medicare_reason text NULL,
    schip integer NULL,
    no_schip_reason text NULL,
    va_medical_services integer NULL,
    no_va_med_reason text NULL,
    employer_provided integer NULL,
    no_employer_provided_reason text NULL,
    cobra integer NULL,
    no_cobra_reason text NULL,
    private_pay integer NULL,
    no_private_pay_reason text NULL,
    state_health_ins integer NULL,
    no_state_health_ins_reason text NULL,
    hivaids_assistance integer NULL,
    no_hivaids_assistance_reason text NULL,
    adap integer NULL,
    no_adap_reason text NULL,
    data_collection_stage,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP NOT NULL,
    user_id integer NULL,
    date_deleted TIMESTAMP NULL,
    export_id integer NULL
);

DROP TABLE IF EXISTS services;
CREATE TABLE services (
    services_id integer NULL,
    project_entry_id integer NULL,
    personal_id integer NULL,
    date_provided TIMESTAMP NULL,
    record_type integer NULL,
    type_provided integer NULL,
    other_type_provided integer NULL,
    sub_type_provided integer NULL,
    fa_amount integer NULL,
    referral_outcome integer NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP NOT NULL,
    user_id integer NULL,
    date_deleted TIMESTAMP NULL,
    export_id integer NULL
);
