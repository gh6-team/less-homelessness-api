package svc.managers;

import org.springframework.stereotype.Component;
import svc.data.shelters.ShelterDAO;
import svc.location.LatLng;
import svc.models.Shelter;
import svc.models.ShelterBedAssignment;

import javax.inject.Inject;
import java.util.List;

@Component
public class ShelterManager {
    @Inject
    private ShelterDAO shelterDAO;

    public Shelter GetShelterById(int shelterId) {
        return shelterDAO.getByShelterId(shelterId);
    }

    public List<Shelter> GetSheltersByLocation(LatLng location) {
        return shelterDAO.getByLocation(location);
    }

    public List<Shelter> GetAllShelters() {
        return shelterDAO.getAllShelters();
    }

    public List<ShelterBedAssignment> GetBedAssignmentsForShelter(int shelterId) {
        return shelterDAO.getBedAssignmentsForShelter(shelterId);
    }

    public ShelterBedAssignment assignBed(ShelterBedAssignment shelterBedAssignment) {
        return shelterDAO.assignBed(shelterBedAssignment);
    }

    public Long GetAvailableBedCount(int shelterId) {
        return shelterDAO.getAvailableBedCount(shelterId);
    }
}
