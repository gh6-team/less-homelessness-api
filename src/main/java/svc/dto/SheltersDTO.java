package svc.dto;

import svc.models.Shelter;

import java.util.List;

public class SheltersDTO {
    public SheltersDTO(List<Shelter> models, List<Long> availableBeds) {
        this.shelters = models;
        this.availableBeds = availableBeds;
    }

    public List<Shelter> shelters;
    public List<Long> availableBeds;
}
