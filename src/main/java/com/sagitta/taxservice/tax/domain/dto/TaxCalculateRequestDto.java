package com.sagitta.taxservice.tax.domain.dto;

import lombok.Data;

@Data
public class TaxCalculateRequestDto {
    private String eTIN;
    private int year;
    private double income;
    private String city;
    private String gender;
    private int age;
}
