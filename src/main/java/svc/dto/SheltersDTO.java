package svc.dto;

import svc.models.Shelter;

import java.util.List;

public class SheltersDTO {
    public SheltersDTO(List<Shelter> models) {
        this.shelters = models;
    }

    public List<Shelter> shelters;
}
