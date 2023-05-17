package com.sourabhsurve.SBT_BatchJob.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

//    @PostMapping("/importCustomers")
//    public void importCsvToDBJob() {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
//        //System.out.println("imported....");
//        try {
//            jobLauncher.run(job, jobParameters);
//        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
//                 JobParametersInvalidException e) {
//            e.printStackTrace();


            @PostMapping("/import")
            public ResponseEntity<String> importCustomers() throws Exception {
                JobParameters jobParameters = new JobParametersBuilder()
                        .addLong("time", System.currentTimeMillis())
                        .toJobParameters();
                JobExecution jobExecution = jobLauncher.run(job, jobParameters);
                return ResponseEntity.ok("Imported " + jobExecution.getStepExecutions()
                                 .iterator().next().getWriteCount() + " customers.");
            }

        }


