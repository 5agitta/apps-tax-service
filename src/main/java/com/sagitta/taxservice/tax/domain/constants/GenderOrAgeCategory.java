package com.sagitta.taxservice.tax.domain.constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GenderOrAgeCategory {
    MALE_UNDER_60(350000),
    FEMALE_OR_OVER_60(400000),
    ;

    private final double theshold;
    public double getTheshold() {
        return theshold;
    }
}
