package pl.sda.currencyclient;

import kong.unirest.JacksonObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Service
public class FixerClient {

    public RatesResponse getRates(LocalDate exchangeDate, String selectedBase) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        Unirest.config().setObjectMapper(new JacksonObjectMapper(mapper));

        FixerResponse response = Unirest.get("http://data.fixer.io/api/" + exchangeDate)
                .queryString("access_key", "acbc96c064ef32725ac5dbe0a2e41942")
                .queryString("symbols", "USD,GBP,PLN")
                .asObject(FixerResponse.class)
                .getBody();

        RatesResponse ratesResponse = toRatesResponse(selectedBase, response);

        return ratesResponse;
    }

    private RatesResponse toRatesResponse(String selectedBase, FixerResponse response) {
        Map<String,Double> rates = new HashMap<>();
        if(selectedBase.equals(response.getBase())){
            rates.put("USD", Double.parseDouble(response.getRates().getUSD()));
            rates.put("PLN", Double.parseDouble(response.getRates().getPLN()));
            rates.put("GBP", Double.parseDouble(response.getRates().getGBP()));
        }else{
            if(selectedBase.equals("PLN")){
                Double eurToPln = response.getEuroToPln();
                rates.put("USD", response.getEuroToUsd()/eurToPln);
                rates.put("GBP", response.getEuroToGbp()/eurToPln);
                rates.put("EUR", 1.0/eurToPln);
            }else if(selectedBase.equals("USD")){
                Double eurToUsd = response.getEuroToUsd();
                rates.put("PLN", response.getEuroToPln()/eurToUsd);
                rates.put("GBP", response.getEuroToGbp()/eurToUsd);
                rates.put("EUR", 1.0/eurToUsd);
            }else if(selectedBase.equals("GBP")){
                Double eurToGbp = response.getEuroToGbp();
                rates.put("USD", response.getEuroToUsd()/eurToGbp);
                rates.put("PLN", response.getEuroToPln()/eurToGbp);
                rates.put("EUR", 1.0/eurToGbp);
            }else{
                throw new IllegalArgumentException("Invalid base currency");
            }
        }
        return new RatesResponse(selectedBase, rates);
    }





}
