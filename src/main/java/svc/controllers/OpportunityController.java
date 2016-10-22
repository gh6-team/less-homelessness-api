package svc.controllers;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import svc.dto.OpportunitiesDTO;
import svc.dto.OpportunityNeedsDTO;
import svc.dto.OpportunityPairingsDTO;
import svc.logging.LogSystem;
import svc.managers.*;
import svc.models.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/opportunities")
public class OpportunityController {	
	@Inject
	OpportunityManager opportunityManager;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	OpportunitiesDTO FindOpportunities(@RequestParam(value = "sponsorId", required = false) Long sponsorId,
									   @RequestParam(value = "courtId", required = false) Long courtId) {
		if (sponsorId == null && courtId == null) {
			LogSystem.LogEvent("Null ids passed to controller::find");
		}
		
		if (sponsorId != null) {
			return new OpportunitiesDTO(opportunityManager.GetOpportunitiesForSponsor(sponsorId));
		} else if (courtId != null) {
			return new OpportunitiesDTO(opportunityManager.GetOpportunitiesForCourt(courtId));
		}
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	Opportunity GetOpportunity(@PathVariable("id") Long opportunityId) {
		if (opportunityId == null) {
			LogSystem.LogEvent("Null id passed to controller");
		}
		
		return opportunityManager.getOpportunity(opportunityId);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	Opportunity CreateOpportunity(@RequestBody Opportunity newOpportunity) {
		if (newOpportunity == null) {
			LogSystem.LogEvent("Null opportunity passed to post.");
			return null;
		}
		
		if (newOpportunity.id != 0) {
			LogSystem.LogEvent("Opportunity with id was passed to post.");
			return null;
		}
		
		return opportunityManager.createOpportunity(newOpportunity);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value="/{id}/needs")
	OpportunityNeedsDTO GetNeedsForOpportunity(@PathVariable("id") Long opportunityId) {
		return new OpportunityNeedsDTO(opportunityManager.getOpportunityNeedsForOpportunity(opportunityId));
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value="/{id}/needs")
	OpportunityNeed CreateOpportunityNeed(@RequestBody OpportunityNeed need) {
		if (need == null) {
			LogSystem.LogEvent("Null opportunity need passed to post.");
			return null;
		}
		
		if (need.id != 0) {
			LogSystem.LogEvent("Opportunity need with id was passed to post.");
			return null;
		}
		
		return opportunityManager.addNeedToOpportunity(need);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/needs/{needId}/pairings")
	OpportunityPairingsDTO GetOpportunityPairingsForNeed(@PathVariable("needId") Long needId) {
		return new OpportunityPairingsDTO(opportunityManager.getOpportunityPairingsForNeed(needId));
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/{opId}/needs/{needId}/pairings")
	OpportunityPairing CreateOpportunityPairing(@RequestBody OpportunityPairing pairing) {
		if (pairing == null) {
			LogSystem.LogEvent("Null opportunity pairing passed to post.");
			return null;
		}
		
		if (pairing.opportunityNeedId == 0) {
			LogSystem.LogEvent("Opportunity pairing with no need id was passed to post.");
			return null;
		}
		
		if (pairing.violationId == 0) {
			LogSystem.LogEvent("Opportunity pairing with no violation id was passed to post.");
			return null;
		}
		
		return opportunityManager.createPairingForNeed(pairing);
	}
}
