package pl.sda.currencyclient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FixerResponse {
    private String base;
    private Rates rates;

    @JsonCreator
    public FixerResponse(@JsonProperty("base") String base, @JsonProperty("rates") Rates rates) {
        this.base = base;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public Rates getRates() {
        return rates;
    }

    public Double getEuroToPln() {
        return Double.parseDouble(rates.getPLN());
    }
    public Double getEuroToUsd(){
        return Double.parseDouble(rates.getUSD());
    }
    public Double getEuroToGbp(){
        return Double.parseDouble(rates.getGBP());
    }
}
