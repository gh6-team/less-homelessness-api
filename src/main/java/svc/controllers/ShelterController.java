package svc.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import svc.dto.ShelterBedAssignmentsDTO;
import svc.dto.SheltersDTO;
import svc.location.LatLng;
import svc.logging.LogSystem;
import svc.managers.ShelterManager;
import svc.models.Shelter;
import svc.models.ShelterBedAssignment;

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
	SheltersDTO FindShelters(@RequestParam(value = "latitude", required = false) Double latitude,
			@RequestParam(value = "longitude", required = false) Double longitude) {

		if (latitude != null && longitude != null) {
			LatLng location = new LatLng(latitude, longitude);
			return new SheltersDTO(shelterManager.GetSheltersByLocation(location));
		}

		if (latitude == null && longitude == null) {
			return new SheltersDTO(shelterManager.GetAllShelters());
		}

		return null;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/bed_assignments")
	ShelterBedAssignmentsDTO FindShelterBedAssignments(@PathVariable("id") Long id) {
		return new ShelterBedAssignmentsDTO(shelterManager.GetBedAssignmentsForShelter(id.intValue()));
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/{id}/bed_assignments")
	ShelterBedAssignment AssignBed(@RequestBody ShelterBedAssignment shelterBedAssignment) {
		if (shelterBedAssignment == null) {
			LogSystem.LogEvent("Null bed assignment passed to post.");
			return null;
		}

		if (shelterBedAssignment.id != 0) {
			LogSystem.LogEvent("shelterBedAssignment with id was passed to post.");
			return null;
		}

		return shelterManager.assignBed(shelterBedAssignment);
	}

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/available_bed_count")
    Long GetAvailableBedCount(@PathVariable("id") Long id) {
        if (id == null) {
            LogSystem.LogEvent("Null id passed to controller");
        }

        return shelterManager.GetAvailableBedCount(id.intValue());
    }



}
