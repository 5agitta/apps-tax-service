package com.sagitta.taxservice;

import com.sagitta.taxservice.tax.TaxRepository;
import com.sagitta.taxservice.tax.TaxService;
import com.sagitta.taxservice.tax.TaxServiceImpl;
import com.sagitta.taxservice.tax.domain.Tax;
import com.sagitta.taxservice.tax.domain.constants.CityCategory;
import com.sagitta.taxservice.tax.domain.dto.TaxRequestDto;
import com.sagitta.taxservice.tax.domain.dto.TaxResponseDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaxServiceTest {

    @Autowired
    private TaxServiceImpl taxService;

    @MockBean
    private TaxRepository taxRepository;

    @Test
    public void testGetOneYearTaxInfo() {
        // Create a sample TaxRequestDto for testing
        TaxRequestDto requestDto = new TaxRequestDto();
        requestDto.setIncome(1000000);
        requestDto.setAge(30);
        requestDto.setCity(CityCategory.DHAKA_OR_CHITTAGONG);
        requestDto.setETIN("ETIN123");
        requestDto.setYear(2023);


        TaxResponseDto responseDto = taxService.getOneYearTaxInfo(requestDto);


        System.out.println(responseDto.getTotalTax());
        // Assert the result based on your expectations and mocked data
        assertEquals(72500, responseDto.getTotalTax());
        // Add more assertions for other properties as needed
    }
}
