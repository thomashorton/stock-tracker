package com.example.stocktracker;

import com.example.stocktracker.model.StockEntity;
import com.example.stocktracker.model.StockWrapper;
import com.example.stocktracker.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockTrackerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StockTrackerApplication.class, args);
	}

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private StockService stockService;

	@Override
	public void run(String ... args) throws Exception {
		StockWrapper appleStock = stockService.findStock("AAPL");
		stockRepository.save(appleStock.toStockEntity());
	}
}
