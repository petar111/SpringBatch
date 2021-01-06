/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springteam.springbatch.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 *
 * @author siux
 */
@Component
public class ExecutionListener extends StepExecutionListenerSupport{

    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionListener.class);
    
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        
        LOGGER.info("Step name: " + stepExecution.getStepName() + " Summary: " + stepExecution.getSummary());
        
        return super.afterStep(stepExecution); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
