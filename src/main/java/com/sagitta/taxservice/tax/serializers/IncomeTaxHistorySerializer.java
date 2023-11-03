package com.sagitta.taxservice.tax.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sagitta.taxservice.tax.domain.IncomeAndTax;
import com.sagitta.taxservice.tax.domain.IncomeTaxHistory;
import com.sagitta.taxservice.tax.domain.dto.RecentYearsSummaryResponseDto;
import com.sagitta.taxservice.tax.domain.dto.TaxHistoryResponseDto;

import java.io.IOException;
import java.util.List;

public class IncomeTaxHistorySerializer extends JsonSerializer<TaxHistoryResponseDto> {
     @Override
    public void serialize(TaxHistoryResponseDto value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        List<IncomeTaxHistory> incomeTaxHistories = value.getIncomeAndTaxes();

        gen.writeStartArray();

        for (IncomeTaxHistory incomeAndTax : incomeTaxHistories) {
            gen.writeStartObject();
            gen.writeObjectField("year", incomeAndTax.getYear());
            gen.writeObjectField("income", incomeAndTax.getIncome());
            gen.writeObjectField("tax", incomeAndTax.getTax());
            gen.writeObjectField("taxPaid", incomeAndTax.getTaxPaid());
            gen.writeObjectField("taxOwed", incomeAndTax.getTaxOwed());
            gen.writeEndObject();
        }

        gen.writeEndArray();
    }
}
