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

import java.util.ArrayList;
import java.util.List;

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
			List<Shelter> shelters = shelterManager.GetSheltersByLocation(location);
			List<Long> availableBeds = findAvailableBedsForShelters(shelters);
			return new SheltersDTO(shelters, availableBeds);
		}

		if (latitude == null && longitude == null) {
			List<Shelter> shelters = shelterManager.GetAllShelters();
			List<Long> availableBeds = findAvailableBedsForShelters(shelters);
			return new SheltersDTO(shelters, availableBeds);
		}

		return null;
	}

	private List<Long> findAvailableBedsForShelters(List<Shelter> shelters) {
		List<Long> availableBeds = new ArrayList<>();
		
		for (Shelter shelter : shelters) {
			availableBeds.add(shelterManager.GetAvailableBedCount(shelter.id));
		}

		return availableBeds;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/bed_assignments")
	ShelterBedAssignmentsDTO FindShelterBedAssignments(@PathVariable("id") Long id) {
		return new ShelterBedAssignmentsDTO(shelterManager.GetBedAssignmentsForShelter(id.intValue()));
	}

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/bed_assignments")
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
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/unassign_bed")
    Boolean UnassignBed(@PathVariable("id") Long id, @RequestParam(value = "bed_assignment_id", required = false) Long bed_assignment_id) {
        if (bed_assignment_id == null) {
            LogSystem.LogEvent("Null bed assignment passed to post.");
            return null;
        }

        return shelterManager.unassignBed(bed_assignment_id.intValue());
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
