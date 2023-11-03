package com.sagitta.taxservice.tax;

import com.sagitta.taxservice.reqres.ApiResponse;
import com.sagitta.taxservice.tax.domain.Tax;
import com.sagitta.taxservice.tax.domain.dto.RecentYearsSummaryRequestDto;
import com.sagitta.taxservice.tax.domain.dto.RecentYearsSummaryResponseDto;
import com.sagitta.taxservice.tax.domain.dto.TaxRequestDto;
import com.sagitta.taxservice.tax.domain.dto.TaxResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping ("/tax")
public class TaxController {
    private final TaxService taxService;

    @PostMapping ("/yearly")
    public TaxResponseDto getYearlyTax(@RequestBody TaxRequestDto taxRequestDTO) {
        return taxService.getOneYearTaxInfo(taxRequestDTO);
    }

    @PostMapping("/calculate")
    public double getTax(@RequestBody TaxRequestDto taxRequestDTO) {
        Tax tax =  taxService.calculateTax(taxRequestDTO);
        return tax.getTotalTax();
    }

    @PostMapping("/recent")
    @ResponseBody
    public RecentYearsSummaryResponseDto getRecentYearsSummary(@RequestBody RecentYearsSummaryRequestDto recentYearsSummaryRequestDto) {
        return taxService.getRecentYearsSummary(recentYearsSummaryRequestDto);
    }



}
