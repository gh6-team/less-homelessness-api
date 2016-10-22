package svc.dto;

import svc.models.ShelterBedAssignment;

import java.util.List;

public class ShelterBedAssignmentsDTO {
    public ShelterBedAssignmentsDTO(List<ShelterBedAssignment> models) {
        this.shelterBeds = models;
    }

    public List<ShelterBedAssignment> shelterBeds;
}
