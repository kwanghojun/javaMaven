package com.batchboot.Job.process;

import java.util.Map;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batchboot.dto.testLowDto;

import lombok.extern.slf4j.Slf4j;


public class ChunkTestItemProcessor implements ItemProcessor<testLowDto,testLowDto> {
    
    @Override
    public testLowDto process(testLowDto dto) throws Exception {
    	System.out.println("{ChunkTestItemProcessor.process}...Start.!");
    	if(dto !=null) {
    		System.out.println(dto.toString());
    	} else {
    		System.out.println("{ChunkTestItemProcessor.process}...dto null.!");
    	}
        return dto;
    }

}

