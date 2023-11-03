package com.sagitta.taxservice.tax;

import com.sagitta.taxservice.tax.domain.IncomeAndTax;
import com.sagitta.taxservice.tax.domain.IncomeTaxHistory;
import com.sagitta.taxservice.tax.domain.Tax;
import com.sagitta.taxservice.tax.domain.constants.CityCategory;
import com.sagitta.taxservice.tax.domain.constants.Gender;
import com.sagitta.taxservice.tax.domain.constants.GenderOrAgeCategory;
import com.sagitta.taxservice.tax.domain.constants.TaxCategory;
import com.sagitta.taxservice.tax.domain.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.*;

@RequiredArgsConstructor
@Service
public class TaxServiceImpl implements TaxService {

    private final TaxRepository taxRepository;

    public TaxResponseDto getOneYearTaxInfo(TaxRequestDto taxRequestDTO) {
        Tax tax = findOrCreateTax(taxRequestDTO);

        return taxEntityToDto(tax);
    }

    private Tax findOrCreateTax(TaxRequestDto taxRequestDTO) {
        return taxRepository.findByEtinAndYear(taxRequestDTO.getETIN(), taxRequestDTO.getYear())
                .orElseGet(() -> createAndSaveTax(taxRequestDTO));
    }

    public Tax calculateTax(TaxRequestDto taxRequestDto) {
        double income = taxRequestDto.getIncome();
        int age = taxRequestDto.getAge();
        Gender gender;
        if (taxRequestDto.getGender().equals(Gender.MALE.getGender()))
            gender = Gender.MALE;
        else gender = Gender.FEMALE;
        CityCategory city;
        if (taxRequestDto.getCity().equals(CityCategory.DHAKA_OR_CHITTAGONG.getName()))
            city = CityCategory.DHAKA_OR_CHITTAGONG;
        else if (taxRequestDto.getCity().equals(CityCategory.OTHER_CITY.getName()))
            city = CityCategory.OTHER_CITY;
        else city = CityCategory.NON_CITY;
        double threshold = 0;
        HashMap<Double, Double> taxCategories;
        if (age <= 60 && gender != Gender.FEMALE) {
            threshold = GenderOrAgeCategory.MALE_UNDER_60.getTheshold();
            taxCategories = getTaxCategoriesForMale();
        } else {
            threshold = GenderOrAgeCategory.FEMALE_OR_OVER_60.getTheshold();
            taxCategories = getTaxCategoriesForFemaleOrSenior();
        }
        if (income <= threshold) return createTax(taxRequestDto, 0, city.getCityCharge());
        double taxAmount = getTaxAmount(income - threshold, taxCategories);
        if (taxAmount < city.getCityCharge())
            taxAmount = city.getCityCharge();
        return createTax(taxRequestDto, taxAmount, city.getCityCharge());


    }

    @Override
    public RecentYearsSummaryResponseDto getRecentYearsSummary(RecentYearsSummaryRequestDto taxRequestDTO) {
        List<IncomeAndTax> incomeAndTaxes = new ArrayList<>();

        int currentYear = Year.now().getValue();

        for (int i = 0; i < 5; i++) {
            int yearToCheck = currentYear - i;
            Optional<Tax> tax = taxRepository.findByEtinAndYear(taxRequestDTO.getEtin(), yearToCheck);
            IncomeAndTax incomeAndTax = new IncomeAndTax();
            if (tax.isPresent()) {
                incomeAndTax.setYear(yearToCheck);
                incomeAndTax.setIncome(tax.get().getTotalIncome());
                incomeAndTax.setTax(tax.get().getTotalTax());
                incomeAndTaxes.add(incomeAndTax);
            }
            else {
                incomeAndTax.setYear(yearToCheck);
                incomeAndTax.setIncome(0.0);
                incomeAndTax.setTax(0.0);
                incomeAndTaxes.add(incomeAndTax);
            }
        }
        RecentYearsSummaryResponseDto response = new RecentYearsSummaryResponseDto(incomeAndTaxes);
        return response;
    }

    public TaxHistoryResponseDto getTaxHistory(String etin) {
        List<IncomeTaxHistory> incomeAndTaxeHistories = new ArrayList<>();

        int currentYear = Year.now().getValue();

        for (int i = 0; i < 5; i++) {
            int yearToCheck = currentYear - i;
            Optional<Tax> tax = taxRepository.findByEtinAndYear(etin, yearToCheck);
            IncomeTaxHistory incomeTaxHistory = new IncomeTaxHistory();
            if (tax.isPresent()) {
                incomeTaxHistory.setYear(yearToCheck);
                incomeTaxHistory.setIncome(tax.get().getTotalIncome());
                incomeTaxHistory.setTax(tax.get().getTotalTax());
                incomeTaxHistory.setTaxPaid(tax.get().getTotalTaxPaid());
                incomeTaxHistory.setTaxOwed(tax.get().getTotalTaxOwed());
                incomeAndTaxeHistories.add(incomeTaxHistory);
            }
            else {
                incomeTaxHistory.setYear(yearToCheck);
                incomeTaxHistory.setIncome(0.0);
                incomeTaxHistory.setTax(0.0);
                incomeTaxHistory.setTaxPaid(0.0);
                incomeTaxHistory.setTaxOwed(0.0);
                incomeAndTaxeHistories.add(incomeTaxHistory);
            }
        }
        TaxHistoryResponseDto response = new TaxHistoryResponseDto(incomeAndTaxeHistories);
        return response;
    }

    public ResponseEntity<String> returnTax(TaxReturnRequestDto taxRequestDto) {
        Tax tax = taxRepository.findByEtinAndYear(taxRequestDto.getEtin(), taxRequestDto.getYear()).get();
        double taxReturn = tax.getTotalTax() - tax.getTotalTaxPaid();
        return ResponseEntity.ok().body("Your tax return is " + taxReturn);
    }

    private double getTaxAmount(double taxableIncome, HashMap<Double, Double> taxCategories) {
        double tax = 0;

        for (Double threshold : taxCategories.keySet()) {
            double rate = taxCategories.get(threshold);

            if (taxableIncome > threshold) {
                tax += threshold * rate;
                taxableIncome -= threshold;
            } else {
                tax += taxableIncome * rate;
                break; // Exit the loop when no more taxable income is left.
            }
        }

        return tax;
    }

    private HashMap<Double, Double> getTaxCategoriesForMale() {
        HashMap<Double, Double> taxThresholds = new HashMap<>();
        taxThresholds.put(100000.0, 0.05);
        taxThresholds.put(300000.0, 0.1);
        taxThresholds.put(400000.0, 0.15);
        taxThresholds.put(500000.0, 0.2);
        taxThresholds.put(1650000.0, 0.2);

        return taxThresholds;
    }

    private HashMap<Double, Double> getTaxCategoriesForFemaleOrSenior() {
        HashMap<Double, Double> taxThresholds = new HashMap<>();
        taxThresholds.put(100000.0, 0.05);
        taxThresholds.put(300000.0, 0.1);
        taxThresholds.put(400000.0, 0.15);
        taxThresholds.put(500000.0, 0.2);
        taxThresholds.put(1700000.0, 0.2);

        return taxThresholds;
    }

    private Tax createTax(TaxRequestDto taxRequestDto, double taxAmount, double cityCharge) {
        Tax tax = Tax.builder()
                .taxId(UUID.randomUUID().toString())
                .etin(taxRequestDto.getETIN())
                .year(taxRequestDto.getYear())
                .totalIncome(taxRequestDto.getIncome())
                .totalTax(taxAmount)
                .totalTaxPaid(0)
                .totalTaxOwed(taxAmount)
                .cityCharge(cityCharge)
                .build();
        return tax;
    }

    public TaxResponseDto taxEntityToDto(Tax tax) {
        TaxResponseDto taxResponseDto = new TaxResponseDto();
        taxResponseDto.setTaxId(tax.getTaxId());
        taxResponseDto.setETIN(tax.getEtin());
        taxResponseDto.setYear(tax.getYear());
        taxResponseDto.setTotalIncome(tax.getTotalIncome());
        taxResponseDto.setTotalTax(tax.getTotalTax());
        taxResponseDto.setTotalTaxPaid(tax.getTotalTaxPaid());
        taxResponseDto.setTotalTaxOwed(tax.getTotalTaxOwed());
        return taxResponseDto;
    }

    private Tax createAndSaveTax(TaxRequestDto taxRequestDTO) {
        Tax tax = calculateTax(taxRequestDTO);
        taxRepository.save(tax);
        return tax;
    }

}
