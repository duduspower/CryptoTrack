package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Crypto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cryptoId;

    private String name;
    private String id;
    private String assertId;
    private String timestamp;

    private LocalDateTime createdAt;

    private int marketCapRank;
    private int volumeRank;

    private double price;
    private double volume;
    private double totalSupply;
    private double freeFloatSupply;
    private double marketCap;
    private double totalMarketCap;

    public Crypto() {
    }

    public Crypto(String name, String id, String assertId, String timestamp, int marketCapRank, int volumeRank, double price, double volume, double totalSupply, double freeFloatSupply, double marketCap, double totalMarketCap) {
        this.name = name;
        this.id = id;
        this.assertId = assertId;
        this.timestamp = timestamp;
        this.createdAt = LocalDateTime.now();
        this.marketCapRank = marketCapRank;
        this.volumeRank = volumeRank;
        this.price = price;
        this.volume = volume;
        this.totalSupply = totalSupply;
        this.freeFloatSupply = freeFloatSupply;
        this.marketCap = marketCap;
        this.totalMarketCap = totalMarketCap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCryptoId() {
        return cryptoId;
    }

    public void setCryptoId(int cryptoId) {
        this.cryptoId = cryptoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssertId() {
        return assertId;
    }

    public void setAssertId(String assertId) {
        this.assertId = assertId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getMarketCapRank() {
        return marketCapRank;
    }

    public void setMarketCapRank(int marketCapRank) {
        this.marketCapRank = marketCapRank;
    }

    public int getVolumeRank() {
        return volumeRank;
    }

    public void setVolumeRank(int volumeRank) {
        this.volumeRank = volumeRank;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public double getFreeFloatSupply() {
        return freeFloatSupply;
    }

    public void setFreeFloatSupply(double freeFloatSupply) {
        this.freeFloatSupply = freeFloatSupply;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public double getTotalMarketCap() {
        return totalMarketCap;
    }

    public void setTotalMarketCap(double totalMarketCap) {
        this.totalMarketCap = totalMarketCap;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
