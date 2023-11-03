package com.sagitta.taxservice.tax;


import com.sagitta.taxservice.tax.domain.Tax;
import com.sagitta.taxservice.tax.domain.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TaxService {

    public TaxResponseDto getOneYearTaxInfo(TaxRequestDto taxRequestDTO);
    Tax calculateTax(TaxRequestDto taxRequestDTO);

    RecentYearsSummaryResponseDto getRecentYearsSummary(RecentYearsSummaryRequestDto taxRequestDTO);

    TaxHistoryResponseDto getTaxHistory(String etin);
    ResponseEntity<String> returnTax(TaxReturnRequestDto taxRequestDto);
}
