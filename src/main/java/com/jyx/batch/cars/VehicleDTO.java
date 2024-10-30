package com.jyx.batch.cars;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDTO {

    private Integer year;
    private String make;
    private String model;
    private String trim;
    private String body;
    private String transmission;
    private String vin;
    private String state;
    private Integer condition;
    private Integer odometer;
    private String color;
    private String interior;
    private String seller;
    private Integer mmr;
    private Integer sellingprice;
    private String saledate;

}
