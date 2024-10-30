package com.jyx.batch.cars;

import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

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

        return dto.getYear() == null || dto.getMake() == null ||
                dto.getModel() == null || dto.getTrim() == null ||
                dto.getBody() == null || dto.getTransmission() == null ||
                dto.getVin() == null || dto.getState() == null ||
                dto.getCondition() == null || dto.getOdometer() == null ||
                dto.getColor() == null || dto.getInterior() == null ||
                dto.getSeller() == null || dto.getMmr() == null ||
                dto.getSellingprice() == null || dto.getSaledate() == null;


    }
}
