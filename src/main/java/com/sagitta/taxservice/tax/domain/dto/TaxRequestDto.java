package com.sagitta.taxservice.tax.domain.dto;


import com.sagitta.taxservice.tax.domain.constants.CityCategory;
import com.sagitta.taxservice.tax.domain.constants.Gender;
import lombok.Data;

@Data
public class TaxRequestDto {
    private String eTIN;
    private int year;
    private double income;
    private String city;
    private String gender;
    private int age;

}
