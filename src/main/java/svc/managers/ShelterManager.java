package svc.managers;

import org.springframework.stereotype.Component;
import svc.data.shelters.ShelterDAO;
import svc.location.LatLng;
import svc.models.Shelter;

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

}
