package com.rms.assessment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.batch.core.JobParameter;

@Controller
public class FIleEncryptController {
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@RequestMapping(value = "/fileEncrypt", method = RequestMethod.GET)
	public String dateForm() {
		return "file-encrypt";
	}
	
	@RequestMapping(value = "/encrypt-file", method = RequestMethod.POST)
	public String processDate(@RequestParam("txtFile") MultipartFile file, @RequestParam String numThreads) {

		if (file.isEmpty() || !"text/plain".equals(file.getContentType())) {
			System.out.println("Empty file or not a text file..............................");
			return "file-encrypt";
		}
		
		//TODO write file to local path
		
		
		//use the file path to pass as job parameter to be able to read file in the batch read step
		
		Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
		parameters.put("inputfile", new JobParameter(file.getOriginalFilename()));
		JobParameters jobParameters = new JobParameters(parameters);

		try {
			jobLauncher.run(job, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "file-encrypt";
	}
}
