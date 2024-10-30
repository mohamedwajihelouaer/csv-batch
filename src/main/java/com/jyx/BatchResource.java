package com.jyx;

import jakarta.batch.runtime.BatchRuntime;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Properties;

@Path("/batch")
public class BatchResource {

    @GET
    @Path("/start")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        long executionId = BatchRuntime.getJobOperator().start("car-job", new Properties());
        return "Batch job started with executionId: " + executionId;
    }
}
