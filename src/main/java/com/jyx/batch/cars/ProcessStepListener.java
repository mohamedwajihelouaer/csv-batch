package com.jyx.batch.cars;

import jakarta.batch.api.listener.AbstractStepListener;
import jakarta.batch.operations.JobOperator;
import jakarta.batch.runtime.context.JobContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.supercsv.io.CsvBeanWriter;

import java.util.Arrays;

@Named("processStepListener")
@ApplicationScoped
@Slf4j
public class ProcessStepListener extends AbstractStepListener {

    private CsvBeanWriter writer;

    private final String[] reportHeader =  new String[]{
            "WRITE_SKIP_COUNT", "COMMIT_COUNT", "WRITE_COUNT", "READ_COUNT", "PROCESS_SKIP_COUNT",
            "READ_SKIP_COUNT", "ROLLBACK_COUNT", "FILTER_COUNT"
    };

    @Inject
    JobContext jobContext;

    @Inject
    JobOperator jobOperator;


    @Override
    @SneakyThrows
    public void beforeStep()  {
    }

    @Override
    @SneakyThrows
    public void afterStep()  {

        var stepExecutions = jobOperator.getStepExecutions(jobContext.getExecutionId());

        stepExecutions.forEach(s -> {
            log.info("BatchStatus {}", s.getBatchStatus());
            log.info("Step Name {}", s.getStepName());
            log.info("ExitStatus {}", s.getExitStatus());
            Arrays.stream(s.getMetrics()).forEach(e -> log.info("{} = {}", e.getType(), e.getValue()));
        });

    }
}
