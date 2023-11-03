package com.sagitta.taxservice.tax.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sagitta.taxservice.tax.serializers.IncomeAndTaxSerializer;
import lombok.Data;

@Data
public class IncomeAndTax {
    private int year;
    private double income;
    private double tax;
}
