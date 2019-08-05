package com.rms.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rms.assessment.config.DatesConfig;
import com.rms.assessment.models.DatesInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Controller
public class DaysCalculatorController {
	
	@Autowired
	DatesConfig config;
   
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
   
	@RequestMapping(value = "/dateForm", method = RequestMethod.GET)
	public String dateForm() {
		return "dateForm";
	}

	@RequestMapping(value = "/process-date", method = RequestMethod.POST)
	public String processDate(@RequestParam String startDate, @RequestParam String endDate,
			@ModelAttribute DatesInfo datesInfo) {
		
		datesInfo.setStartDate(startDate);
		datesInfo.setEndDate(endDate);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
		LocalDate endLocalDate = LocalDate.parse(endDate, formatter);
		
		if(endLocalDate.isBefore(startLocalDate)) {
			datesInfo.setError(config.getInvalidDatesError());
		} else {
			long daysBetween = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
			
			datesInfo.setDaysBetween(daysBetween);
		}

		return "dateForm";
	}
}
