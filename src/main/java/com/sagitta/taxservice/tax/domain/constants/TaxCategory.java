package com.sagitta.taxservice.tax.domain.constants;

public enum TaxCategory {
    CATEGORY_1(0.05),
    CATEGORY_2(0.1),
    CATEGORY_3(0.15),
    CATEGORY_4(0.2),
    CATEGORY_5(0.25),
    ;



    private final double taxRate;
    TaxCategory(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTaxRate() {
        return taxRate;
    }
}
