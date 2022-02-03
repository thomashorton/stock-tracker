package com.example.stocktracker.model;

import lombok.Data;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class StockWrapper {
    private final Stock stock;
    private final LocalDateTime accessTime;
    private final String ticker;

    public StockWrapper(String ticker) throws IOException {
        this.stock = YahooFinance.get(ticker);
        this.accessTime = LocalDateTime.now();
        this.ticker = ticker;
    }

    public StockEntity toStockEntity() {
        StockQuote quote = this.getStock().getQuote();
        StockEntity stockEntity = new StockEntity();
        stockEntity.setTicker(this.ticker);
        stockEntity.setCurrency(this.getStock().getCurrency());
        stockEntity.setCurrentPrice(quote.getPrice());
        stockEntity.setCompanyName(this.getStock().getName());
        stockEntity.setOpen(quote.getOpen());
        stockEntity.setPreviousClose(quote.getPreviousClose());
        stockEntity.setLastUpdated(Instant.now());
        return stockEntity;
    }

    public void updateStockEntity() {

    }
}
