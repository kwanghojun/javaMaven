package com.batchboot.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
public class StepExecutionNotificationListener extends StepExecutionListenerSupport {

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
//        log.info("afterStep");
    	System.out.println("StepExecutionNotificationListener..afterStep..!");
        return super.afterStep(stepExecution);
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
//        log.info("beforeStep ");
    	System.out.println("StepExecutionNotificationListener..beforeStep..!");	
        super.beforeStep(stepExecution);
    }

}