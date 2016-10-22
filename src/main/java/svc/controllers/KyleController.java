package svc.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import svc.dto.CitationSearchCriteria;
import svc.managers.CitationManager;
import svc.models.Citation;

@RestController
@EnableAutoConfiguration
@RequestMapping("/kyle")
public class KyleController {
	@Inject 
	CitationManager citationManager;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Integer GetResult()
	{
		String inputString = "05-06-1981"; 
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		CitationSearchCriteria criteria = new CitationSearchCriteria();
		criteria.citation_number = "747199692";
		Calendar dob = Calendar.getInstance();
		dob.set(1981, 5, 5, 0, 0, 0);
		criteria.date_of_birth = dob.getTime();
		try {
			Date inputDate = dateFormat.parse(inputString);
			//criteria.date_of_birth = inputDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Citation> citations = citationManager.FindCitations(criteria);
		Integer total = citations.size();
		return total;
	}
}
