package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/currency")
public class CurrencyController {

    private JpaCurrencyRepo repo;

    public CurrencyController(JpaCurrencyRepo repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<?> addCurrency(@RequestBody Currencies currencies){
        repo.save(currencies);
        return ResponseEntity.ok("Currency added");
    }

    @GetMapping
    public ResponseEntity<?> getCurrency(){
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/by")
    public ResponseEntity<?> getCurrencyBySimpleId(){
        return ResponseEntity.ok(repo.readCurenciesBySimplyId("btc"));
    }
}
