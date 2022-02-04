package com.example.stocktracker.controller;

import com.example.stocktracker.StockRepository;
import com.example.stocktracker.model.StockEntity;
import com.example.stocktracker.model.StockWrapper;
import com.example.stocktracker.service.StockService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yahoofinance.Stock;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }
    @Autowired
    private StockRepository stockRepository;

    @GetMapping("stocks/")
    public List<StockEntity> getStocks() {
        return this.stockRepository.findAll();
    }

    @PutMapping("stocks/{ticker}")
    public ResponseEntity addStock(@PathVariable String ticker) {
        try {
            System.out.println(ticker);
            StockWrapper stockWrapper = stockService.findStock(ticker);
            stockRepository.save(stockWrapper.toStockEntity());
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Saved stock, ticker: %s was valid", ticker));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID TICKER");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
        }
    }

    @PostMapping("stocks/refresh")
    public ResponseEntity refreshStocks(){
        try {
            stockRepository.findAll().forEach(stockEntity -> {
                try {
                    String ticker = stockEntity.getTicker();
                    StockWrapper stockWrapper = stockService.findStock(ticker);
                    StockQuote updatedQuote = stockWrapper.getStock().getQuote();
                    stockEntity.setCurrentPrice(updatedQuote.getPrice());
                    stockEntity.setOpen(updatedQuote.getOpen());
                    stockEntity.setPreviousClose(updatedQuote.getPreviousClose());
                    stockEntity.setLastUpdated(Instant.now());
                    stockRepository.save(stockEntity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return ResponseEntity.status(HttpStatus.OK).body("Refreshed Stocks");
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
        }
    }

    @DeleteMapping("stocks/{ticker}")
    public ResponseEntity deleteStock(@PathVariable String ticker) {
        try {
            stockRepository.deleteById(ticker);
            return ResponseEntity.status(HttpStatus.OK).body("deleted stock from respository");
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
        }

    }
}
