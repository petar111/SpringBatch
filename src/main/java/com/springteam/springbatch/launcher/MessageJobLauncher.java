/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springteam.springbatch.launcher;

import java.io.File;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.messaging.Message;

/**
 *
 * @author siux
 */
public class MessageJobLauncher {
    private final Job job;
    private final String filenameKey;

    public MessageJobLauncher(Job job, String filenameKey) {
        this.job = job;
        this.filenameKey = filenameKey;
    }
    
    
    public JobLaunchRequest toRequest(Message<File> message){
        JobParametersBuilder builder = new JobParametersBuilder();
        
        builder.addString(filenameKey, message.getPayload().getAbsolutePath());
        
        return new JobLaunchRequest(job, builder.toJobParameters());
    }

    
    
    
    
    
    
    
}
