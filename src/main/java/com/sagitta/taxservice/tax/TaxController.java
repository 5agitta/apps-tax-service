package com.sagitta.taxservice.tax;

import com.sagitta.taxservice.reqres.ApiResponse;
import com.sagitta.taxservice.tax.domain.Tax;
import com.sagitta.taxservice.tax.domain.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/history")
    @ResponseBody
    public TaxHistoryResponseDto getTaxHistory(@RequestBody String etin) {
        return taxService.getTaxHistory(etin);
    }

    @PostMapping("/return")
    @ResponseBody
    public ResponseEntity<String> returnTax(@RequestBody TaxReturnRequestDto taxRequestDto) {
        Tax tax = taxService.calculateTax(taxRequestDto);
        return ResponseEntity.ok().body("Your tax return is " + tax.getTotalTax());
    }



}
