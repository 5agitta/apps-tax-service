package com.sagitta.taxservice.tax;

import com.sagitta.taxservice.reqres.ApiResponse;
import com.sagitta.taxservice.tax.domain.dto.TaxRequestDto;
import com.sagitta.taxservice.tax.domain.dto.TaxResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping ("/tax")
public class TaxController {
    private final TaxService taxService;

    @PostMapping ("/yearly")
    public TaxResponseDto getYearlyTax(@RequestBody TaxRequestDto taxRequestDTO) {
        return taxService.getOneYearTaxInfo(taxRequestDTO);
    }



}
