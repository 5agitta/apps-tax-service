package com.sagitta.taxservice.tax.domain;

import com.sagitta.taxservice.tax.domain.constants.GenderOrAgeCategory;
import com.sagitta.taxservice.tax.domain.constants.TaxCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Setter
@Data
@Table(schema = "tax")
public class Tax {
    @Id
    private String taxId;
    private String etin;
    private int year;
    private double totalIncome;
    private double threshold;
    private double taxableIncome;
    private TaxCategory taxCategory;
    private double taxRate;
    private GenderOrAgeCategory genderOrAgeCategory;
    private double cityCharge;
    private double totalCharge;
    private double totalTax;
    private double totalTaxPaid;
    private double totalTaxOwed;
}
