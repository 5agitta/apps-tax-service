package com.sagitta.taxservice.tax.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sagitta.taxservice.tax.domain.IncomeAndTax;
import com.sagitta.taxservice.tax.serializers.IncomeAndTaxSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@JsonSerialize(using = IncomeAndTaxSerializer.class)
public class RecentYearsSummaryResponseDto {

    private List<IncomeAndTax> incomeAndTaxes;
}
