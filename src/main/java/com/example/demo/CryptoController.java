package com.example.demo;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/crypto")
public class CryptoController{

    private JpaCryptoRepo repo;
    private CurrencyService currencyService;

    public CryptoController(JpaCryptoRepo repo, CurrencyService currencyService) {
        this.repo = repo;
        this.currencyService = currencyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getApiDataBySimpleId(@PathVariable("id") String id) throws IOException, InterruptedException {
        Crypto crypto = getApiData(id);

        return ResponseEntity.ok(repo.save(crypto));
    }

    public Crypto getApiData(String simpleId) throws IOException, InterruptedException {  //pobiera dane z api i przerabia na obiekt

        String searchId = currencyService.giveAdvancedKey(simpleId);
        System.out.println("Search ID : " + searchId);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://bravenewcoin.p.rapidapi.com/market-cap?assetId=" + searchId))
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik5EVXhNRGhHT0VReE56STVOelJCTTBJM1FrUTVOa0l4TWtRd1FrSTJSalJFTVRaR1F6QTBOZyJ9.eyJpc3MiOiJodHRwczovL2F1dGguYnJhdmVuZXdjb2luLmNvbS8iLCJzdWIiOiJvQ2RRb1pvSTk2RVJFOUhZM3NRN0ptYkFDZkJmNTVSWUBjbGllbnRzIiwiYXVkIjoiaHR0cHM6Ly9hcGkuYnJhdmVuZXdjb2luLmNvbSIsImlhdCI6MTY1MDIyOTkzOCwiZXhwIjoxNjUwMzE2MzM4LCJhenAiOiJvQ2RRb1pvSTk2RVJFOUhZM3NRN0ptYkFDZkJmNTVSWSIsInNjb3BlIjoicmVhZDppbmRleC10aWNrZXIgcmVhZDpyYW5raW5nIHJlYWQ6bXdhIHJlYWQ6Z3dhIHJlYWQ6YWdncmVnYXRlcyByZWFkOm1hcmtldCByZWFkOmFzc2V0IHJlYWQ6b2hsY3YgcmVhZDptYXJrZXQtY2FwIiwiZ3R5IjoiY2xpZW50LWNyZWRlbnRpYWxzIn0.bZjAhOVr9Bta-4VNTsoA-sg38JSp0uPgxw3ICia6f20RVDYER_X8EW_v2m_58vS9N6z-I4rDXcK3uH3aJ_tKevhDBlxjFDUpDCP1eOh2Mziu2H7URAL3m4QVmR4Kv2XRo9tBb7ynNldzopdob6EG9_YGtoKxVmMhDXBM9ciI3rWB79FcsB2TGakGrfACeEfYDkEtFXh5AUvTGdmnFVIddQegfgSti1HyOw3y4qoC77L_e_bPJlhcer5tRDho_fOSKakRW10Mwhs1P3Bpv2aQuykWr8qaLaB48Mplt7drqp1rpCSOXsqAWhEXhZ_EaU5w9cX2kaTe7LYf5Wro36VJ1Q")
                .header("X-RapidAPI-Host", "bravenewcoin.p.rapidapi.com")
                .header("X-RapidAPI-Key", "2e8087b636msh384464746937759p1941f9jsn9c7a58296c33")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        String answer = response.body();
        char ch = '"';
        System.out.println(answer);

        answer = answer.replace("[","");
        answer = answer.replace("]", "");
        answer = answer.replace("{","");
        answer = answer.replace("}","");
        answer = answer.replace(Character.toString(ch),"");
        answer = answer.replace("content","");
        answer = answer.substring(1,answer.length());

        System.out.println(answer);

        String[] answers = answer.split(",");

        System.out.println("Answers : ");
        for(int i = 0; i < answers.length; i++){
            System.out.println(answers[i]);
        }

        answers = parseAnswers(answers);

        String name = simpleId;
        String id = answers[0];
        String assetId = answers[1];
        String timestamp = answers[2];

        int marketCapRank = Integer.parseInt(answers[3]);
        int volumeRank = Integer.parseInt(answers[4]);

        double price = Double.parseDouble(answers[5]);
        double volume = Double.parseDouble(answers[6]);
        double totalSupply = Double.parseDouble(answers[7]);
        double freeFloatSupply = Double.parseDouble(answers[8]);
        double marketCap = Double.parseDouble(answers[9]);
        double totalMarketCap = Double.parseDouble(answers[10]);

        return new Crypto(name,id,assetId,timestamp,marketCapRank,volumeRank,price,volume,totalSupply,freeFloatSupply,marketCap,totalMarketCap);
    }

    public String[] parseAnswers(String[] answers){
        answers[0] = answers[0].substring(3, answers[0].length());
        answers[1] = answers[1].substring(8, answers[1].length());
        answers[2] = answers[2].substring(10, answers[2].length());
        answers[3] = answers[3].substring(14, answers[3].length());
        answers[4] = answers[4].substring(11, answers[4].length());
        answers[5] = answers[5].substring(6, answers[5].length());
        answers[6] = answers[6].substring(7, answers[6].length());
        answers[7] = answers[7].substring(12, answers[7].length());
        answers[8] = answers[8].substring(16, answers[8].length());
        answers[9] = answers[9].substring(10, answers[9].length());
        answers[10] = answers[10].substring(15, answers[10].length());

        return answers;
    }

}
