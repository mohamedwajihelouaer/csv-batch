package com.jyx.batch.cars;

import jakarta.batch.api.AbstractBatchlet;
import jakarta.batch.operations.JobOperator;
import jakarta.batch.runtime.context.JobContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@ApplicationScoped
@Named("cleanUp")
@Slf4j
public class CleanUp extends AbstractBatchlet {


    @Override
    public String process() throws Exception {
        return "COMPLETED";
    }
}
