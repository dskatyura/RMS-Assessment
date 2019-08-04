package com.rms.assessment.models;

import org.springframework.context.annotation.ComponentScan;

import lombok.Data;

@Data
@ComponentScan
public class DatesInfo {
	 String startDate = null;
	 String endDate = null;
	 long daysBetween = 0;
	 
	 String error = null;
}
