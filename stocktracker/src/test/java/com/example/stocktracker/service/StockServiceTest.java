package com.example.stocktracker.service;

import com.example.stocktracker.model.StockWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceTest {

    final String VALID_STOCK_TICKER = "AAPL";
    final String INVALID_STOCK_TICKER= "";
    @Autowired
    private StockService stockService;

    @Test
    void getValidStock_returnsStockWrapper() throws IOException{
        final StockWrapper stock = stockService.findStock(VALID_STOCK_TICKER);
        assertNotNull(stock);
        System.out.println(stock);
    }

    @Test
    void getQuote_returnsQuote() throws IOException{
        final StockWrapper stock = stockService.findStock(VALID_STOCK_TICKER);
        assertNotNull(stock);
        final StockQuote quote = stockService.getQuote(stock);
        System.out.println(stock);
        System.out.println(quote);
    }

    @Test
    void getInvalidStock_throwsException() {
        assertThrows(IOException.class, () -> {
            stockService.findStock(INVALID_STOCK_TICKER);
        });
    }
}