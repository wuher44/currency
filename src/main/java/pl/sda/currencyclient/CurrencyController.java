package pl.sda.currencyclient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class CurrencyController {

    public final FixerClient fixerClient;

    public CurrencyController(FixerClient fixerClient) {
        this.fixerClient = fixerClient;
    }

    @GetMapping("/currency")
    public String getCurrencies(Model model) {

        LocalDate exDate = LocalDate.now();
        model.addAttribute("currenciesRates", fixerClient.getRates(exDate, "EUR"));
        model.addAttribute("selectedDate", exDate);
        model.addAttribute("prevSel", "EUR");
        return "currency";
    }

    @PostMapping("/currency")
    public String findCurrencies(@RequestParam String exchangeDate,
                                 @RequestParam String selectedBase,
                                 Model model) {

        LocalDate exDate = LocalDate.parse(exchangeDate);
        model.addAttribute("currenciesRates", fixerClient.getRates(exDate, selectedBase));
        model.addAttribute("selectedDate", exchangeDate);
        model.addAttribute("prevSel", selectedBase);

        return "currency";
    }
}

