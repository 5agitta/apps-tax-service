package com.sagitta.taxservice.tax;


import com.sagitta.taxservice.tax.domain.dto.TaxRequestDto;
import com.sagitta.taxservice.tax.domain.dto.TaxResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface TaxService {

    public TaxResponseDto getOneYearTaxInfo(TaxRequestDto taxRequestDTO);
}