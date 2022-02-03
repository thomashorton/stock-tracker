package com.example.stocktracker.service;

import com.example.stocktracker.model.StockEntity;
import com.example.stocktracker.model.StockWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class StockService {
    public StockWrapper findStock(final String ticker) throws IOException {
        return new StockWrapper(ticker);
    }

//    public BigDecimal getPrice(final StockWrapper stock) throws IOException {
//        return stock.getStock().getQuote(true).getPrice();
//    }

    public StockQuote getQuote(final StockWrapper stock) throws IOException {
        return stock.getStock().getQuote();
    }

    public void updateStock(StockEntity stock) throws IOException {
        StockWrapper stockWrapper = findStock(stock.getTicker());

    }

}
