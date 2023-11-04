package com.sagitta.taxservice.tax.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sagitta.taxservice.tax.domain.IncomeAndTax;
import com.sagitta.taxservice.tax.domain.IncomeTaxHistory;
import com.sagitta.taxservice.tax.serializers.IncomeAndTaxSerializer;
import com.sagitta.taxservice.tax.serializers.IncomeTaxHistorySerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@JsonSerialize(using = IncomeTaxHistorySerializer.class)
public class TaxHistoryResponseDto {
    private List<IncomeTaxHistory> incomeAndTaxes;
}
