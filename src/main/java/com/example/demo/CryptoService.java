package com.example.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CryptoService {

    private JpaCryptoRepo repo;
    private CurrencyService currencyService;

    public CryptoService(JpaCryptoRepo repo, CurrencyService currencyService) {
        this.repo = repo;
        this.currencyService = currencyService;
    }

    @Scheduled(fixedDelay = 1800000)  //wykonuje co 30min
    public void getAvrPrice() throws IOException, InterruptedException {
        getDataFixedDelay("btc");
        getDataFixedDelay("eth");
        getDataFixedDelay("ape");
        getDataFixedDelay("cro");
    }



    public void getDataFixedDelay(String simplyId) throws IOException, InterruptedException {

    CryptoController cryptoController = new CryptoController(repo,currencyService);
    Crypto crypto = cryptoController.getApiData(simplyId);

    calculateAvrPrice(simplyId);
    repo.save(crypto);
    crypto.toString();
    }

    public void calculateAvrPrice(String simplyId){
        List<Crypto> cryptos = repo.readCryptoByName(simplyId);
        ArrayList<Double> advPrice = new ArrayList<>();

        Double finalAvrPrice = Double.valueOf(0);

        for(int i = 0; i < cryptos.size(); i++){
            advPrice.add(cryptos.get(i).getPrice());
        }

        for(int j = 0; j < advPrice.size(); j++){
            finalAvrPrice += advPrice.get(j);
        }

        finalAvrPrice = finalAvrPrice / advPrice.size();

        System.out.println("Average price of " + simplyId + ": " + finalAvrPrice);
    }
}
