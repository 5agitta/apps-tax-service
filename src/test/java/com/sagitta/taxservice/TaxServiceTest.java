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

import java.util.HashMap;
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
        requestDto.setCity(CityCategory.DHAKA_OR_CHITTAGONG.getName());
        requestDto.setETIN("ETIN123");
        requestDto.setYear(2023);


        TaxResponseDto responseDto = taxService.getOneYearTaxInfo(requestDto);


        System.out.println(responseDto.getTotalTax());
        // Assert the result based on your expectations and mocked data
        assertEquals(72500, responseDto.getTotalTax());
        // Add more assertions for other properties as needed
    }

     @Test
    public void testGetTaxAmount() {
        // Create a test taxCategories map with tax thresholds and rates
        HashMap<Double, Double> taxCategories = new HashMap<>();
        taxCategories.put(100000.0, 0.05);
        taxCategories.put(300000.0, 0.1);
        taxCategories.put(400000.0, 0.15);
        taxCategories.put(500000.0, 0.2);
        taxCategories.put(1650000.0, 0.2);

        // Test case 1: taxableIncome is 5000, which is below the first threshold.
        double tax1 = taxService.getTaxAmount(50000, taxCategories);
        assertEquals(5000, tax1, 0.001);

        // Test case 2: taxableIncome is 15000, which is between the first and second thresholds.
        double tax2 = taxService.getTaxAmount(650000, taxCategories);
        assertEquals(72500, tax2, 0.001);
//
//        // Test case 3: taxableIncome is 25000, which is between the second and third thresholds.
        double tax3 = taxService.getTaxAmount(25000, taxCategories);
        assertEquals(2500, tax3, 0.001);
//
//        // Test case 4: taxableIncome is 35000, which is above all thresholds.
        double tax4 = taxService.getTaxAmount(35000, taxCategories);
        assertEquals(3500, tax4, 0.001);
    }
}
