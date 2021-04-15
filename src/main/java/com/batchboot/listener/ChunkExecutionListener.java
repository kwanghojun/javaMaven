package com.batchboot.listener;

import org.springframework.batch.core.listener.ChunkListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
public class ChunkExecutionListener extends ChunkListenerSupport {

    @Override
    public void afterChunk(ChunkContext context) {
        super.afterChunk(context);
    }

    @Override
    public void beforeChunk(ChunkContext context) {
        context.attributeNames();
        super.beforeChunk(context);
    }

}