package svc.models;

import java.util.Date;

public class ShelterBedAssignment {
    public int id;
    public int shelter_bed_id;
    public int assigned_to_client_id;
    public Date assignment_date;
    public int assigned_by_id;

    public int shelter_id;
    public String bed_name;
}
