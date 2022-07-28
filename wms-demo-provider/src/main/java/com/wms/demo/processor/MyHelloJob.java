package com.wms.demo.processor;

import com.alibaba.schedulerx.worker.domain.JobContext;
import com.alibaba.schedulerx.worker.processor.JavaProcessor;
import com.alibaba.schedulerx.worker.processor.ProcessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyHelloJob extends JavaProcessor {
    private static final Logger logger = LoggerFactory.getLogger(MyHelloJob.class);

    @Override
    public ProcessResult process(JobContext context) throws Exception {
        logger.info("==================================================  hello schedulerx2.0");
        return new ProcessResult(true);
    }
}
