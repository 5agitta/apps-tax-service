package com.sagitta.taxservice.tax.domain.dto;

import lombok.Data;

@Data
public class TaxReturnRequestDto {
    private String etin;
    private int year;
    private double income;
    private double taxPaid;
}
