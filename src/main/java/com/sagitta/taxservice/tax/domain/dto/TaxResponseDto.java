package com.sagitta.taxservice.tax.domain.dto;

import lombok.Data;

@Data
public class TaxResponseDto {
    private String taxId;
    private String eTIN;
    private int year;
    private double totalIncome;
    private double totalTax;
    private double totalTaxPaid;
    private double totalTaxOwed;
}
