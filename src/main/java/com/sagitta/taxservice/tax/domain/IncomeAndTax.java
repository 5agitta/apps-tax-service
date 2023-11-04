package com.sagitta.taxservice.tax.domain;


import lombok.Data;

@Data
public class IncomeAndTax {
    private int year;
    private double income;
    private double tax;
}
