package com.sagitta.taxservice.tax;


import com.sagitta.taxservice.tax.domain.Tax;
import com.sagitta.taxservice.tax.domain.dto.RecentYearsSummaryRequestDto;
import com.sagitta.taxservice.tax.domain.dto.RecentYearsSummaryResponseDto;
import com.sagitta.taxservice.tax.domain.dto.TaxRequestDto;
import com.sagitta.taxservice.tax.domain.dto.TaxResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface TaxService {

    public TaxResponseDto getOneYearTaxInfo(TaxRequestDto taxRequestDTO);
    Tax calculateTax(TaxRequestDto taxRequestDTO);

    RecentYearsSummaryResponseDto getRecentYearsSummary(RecentYearsSummaryRequestDto taxRequestDTO);
}
