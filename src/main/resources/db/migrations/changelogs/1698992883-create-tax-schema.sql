-- liquibase formatted sql
--- SELECT EXTRACT(EPOCH FROM NOW());
-- changeset Abbiirr:1698992883-1

CREATE TABLE tax.tax (
    tax_id VARCHAR(255) PRIMARY KEY,
    eTIN VARCHAR(255),
    year INT,
    total_income NUMERIC(10, 2),
    threshold NUMERIC(10, 2),
    taxable_income NUMERIC(10, 2),
    tax_category VARCHAR(255),
    tax_rate NUMERIC(5, 2),
    gender_or_age_category VARCHAR(255),
    city_charge NUMERIC(10, 2),
    total_charge NUMERIC(10, 2),
    total_tax NUMERIC(10, 2),
    total_tax_paid NUMERIC(10, 2),
    total_tax_owed NUMERIC(10, 2)
);
