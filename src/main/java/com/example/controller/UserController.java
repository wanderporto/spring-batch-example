package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load")
public class UserController {

	@Autowired
	JobLauncher jobLaucher;
	
	@Autowired
	Job job;
	
	@GetMapping
	public BatchStatus load() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Map<String,JobParameter> maps = new HashMap<>();
		maps.put("time", new JobParameter(System.currentTimeMillis()));
		
		JobParameters parameters = new JobParameters(maps);
		JobExecution jobExecution = (JobExecution) jobLaucher.run(job,parameters);
		
		System.out.println("JobExecution " + jobExecution.getBatchStatus());
		
		return jobExecution.getBatchStatus();
	}
}
