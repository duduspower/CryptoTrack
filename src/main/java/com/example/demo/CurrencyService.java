package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class CurrencyService {
    private JpaCurrencyRepo repo;

    public CurrencyService(JpaCurrencyRepo repo) {
        this.repo = repo;
    }

    public String giveAdvancedKey(String simpleId){

        List<Currencies> currencies = repo.readCurenciesBySimplyId(simpleId);
        Currencies currency = currencies.get(0);

        return currency.getAdvancedId();
    }

}
