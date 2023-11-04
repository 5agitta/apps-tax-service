package com.sagitta.taxservice.tax.domain;

import lombok.Data;

@Data
public class IncomeTaxHistory {
    private int year;
    private double income;
    private double tax;
    private double taxPaid;
    private double taxOwed;
}
