package com.sagitta.taxservice.tax.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sagitta.taxservice.tax.domain.IncomeAndTax;
import com.sagitta.taxservice.tax.domain.dto.RecentYearsSummaryResponseDto;

import java.io.IOException;
import java.util.List;

public class IncomeAndTaxSerializer extends JsonSerializer<RecentYearsSummaryResponseDto> {

     @Override
    public void serialize(RecentYearsSummaryResponseDto value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        List<IncomeAndTax> incomeAndTaxes = value.getIncomeAndTaxes();

        gen.writeStartArray();

        for (IncomeAndTax incomeAndTax : incomeAndTaxes) {
            gen.writeStartObject();
            gen.writeObjectField("year", incomeAndTax.getYear());
            gen.writeObjectField("income", incomeAndTax.getIncome());
            gen.writeObjectField("tax", incomeAndTax.getTax());
            gen.writeEndObject();
        }

        gen.writeEndArray();
    }
}

