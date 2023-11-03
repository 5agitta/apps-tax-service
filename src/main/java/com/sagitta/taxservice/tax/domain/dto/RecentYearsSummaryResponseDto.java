package com.sagitta.taxservice.tax.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sagitta.taxservice.tax.domain.NestedHashMapSerializer;
import lombok.AllArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
public class RecentYearsSummaryResponseDto {
    @JsonSerialize(using = NestedHashMapSerializer.class)
    private HashMap<Integer, HashMap<Double, Double>> incomeTaxMap;
}
