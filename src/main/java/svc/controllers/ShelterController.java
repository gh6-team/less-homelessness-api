package svc.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import svc.dto.SheltersDTO;
import svc.location.LatLng;
import svc.logging.LogSystem;
import svc.managers.ShelterManager;
import svc.models.Shelter;

import javax.inject.Inject;

@RestController
@EnableAutoConfiguration
@RequestMapping("/shelters")
public class ShelterController {
    @Inject
    ShelterManager shelterManager;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Shelter GetShelter(@PathVariable("id") Long id) {
        if (id == null) {
            LogSystem.LogEvent("Null id passed to controller");
        }

        return shelterManager.GetShelterById(id.intValue());
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    SheltersDTO FindShelters(@RequestParam(value = "latitude", required = true) Double latitude,
                             @RequestParam(value = "longitude", required = true) Double longitude) {

        LatLng location = new LatLng(latitude, longitude);
        return new SheltersDTO(shelterManager.GetSheltersByLocation(location));
    }


}
