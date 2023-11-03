package com.sagitta.taxservice.tax.domain.constants;

//@RequiredArgsConstructor
public enum CityCategory {
    DHAKA_OR_CHITTAGONG("Dhaka_Or_Chittagong", 50000),
    OTHER_CITY("Other_City", 4000),
    NON_CITY("Non_City", 3000)
    ;
    private String name;

    private final double cityCharge;

    CityCategory(String name, double cityCharge) {
        this.name = name;
        this.cityCharge = cityCharge;
    }


    public double getCityCharge() {
        return cityCharge;
    }

    public String getName() {
        return name;
    }

}
