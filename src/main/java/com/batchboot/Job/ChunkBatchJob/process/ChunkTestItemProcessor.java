package com.batchboot.Job.ChunkBatchJob.process;

import java.util.Map;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batchboot.dto.testLowDto;

import lombok.extern.slf4j.Slf4j;


public class ChunkTestItemProcessor implements ItemProcessor<testLowDto,testLowDto> {
    private int processCnt=0;
    @Override
    public testLowDto process(testLowDto dto) throws Exception {
    	processCnt+=1;
    	//System.out.println("{ChunkTestItemProcessor.process}...Count.["+processCnt+"]..dto..[]");
    	if(dto !=null) {
    		//System.out.println(dto.toString());
    	} else {
    		System.out.println("{ChunkTestItemProcessor.process}...dto null.!"+dto.toString());
    	}
    	if(processCnt==15) {
    		//Integer.parseInt("abc");
    	}
    	dto.setBigo("COPY-"+dto.getBigo().toUpperCase());
    	dto.setGrp_id(dto.getGrp_id().toUpperCase());
    	dto.setCol1("COPY-"+dto.getCol1().toUpperCase());
    
        return dto;
    }

}

