package pl.sda.currencyclient;

import java.util.Map;

public class RatesResponse {
    private String base;
    private Map<String, Double> rates;

    public RatesResponse(String base, Map<String, Double> rates) {
        this.base = base;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
