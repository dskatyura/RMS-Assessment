package com.rms.assessment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@Data
@PropertySource("classpath:datesInfo.properties")
@ComponentScan
public class DatesConfig {
	@Value("${invalid.dates.error.message}")
	private String invalidDatesError;
}

