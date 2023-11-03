package com.sagitta.taxservice.tax.domain.constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CityCategory {
    DHAKA_OR_CHITTAGONG(50000),
    OTHER_CITY(4000),
    NON_CITY(3000)
    ;

    private final double cityCharge;

    public double getCityCharge() {
        return cityCharge;
    }

}
