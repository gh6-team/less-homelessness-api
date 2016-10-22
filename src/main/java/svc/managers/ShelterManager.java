package svc.managers;

import org.springframework.stereotype.Component;
import svc.data.shelters.ShelterDAO;
import svc.models.Shelter;

import javax.inject.Inject;

@Component
public class ShelterManager {
    @Inject
    private ShelterDAO shelterDAO;

    public Shelter GetShelterById(int shelterId) {
        return shelterDAO.getByShelterId(shelterId);
    }

}
