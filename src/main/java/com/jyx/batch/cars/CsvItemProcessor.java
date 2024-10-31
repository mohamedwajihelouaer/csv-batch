package com.jyx.batch.cars;

import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.Objects;
import java.util.stream.Stream;

@ApplicationScoped
@Named("csvItemProcessor")
public class CsvItemProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object o) throws Exception {

        VehicleDTO dto = (VehicleDTO) o;
        if (validateBean(dto) || dto.getSellingprice() < 50_000) {
            return null;
        }

        return dto;
    }

    private boolean validateBean(VehicleDTO dto) {

        return Stream.of(dto.getYear(), dto.getMake(), dto.getModel(), dto.getTrim(), dto.getBody(), dto.getTransmission(),
                dto.getVin(), dto.getState(), dto.getCondition(), dto.getOdometer(), dto.getColor(), dto.getInterior(),
                dto.getSeller(), dto.getMmr(), dto.getSellingprice(), dto.getSaledate()).allMatch(Objects::nonNull);

    }
}
