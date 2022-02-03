package com.example.stocktracker.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "stocks")
public class StockEntity {


    @Id
    @Column(name="ticker")
    private String ticker;

    @Column(name="company_name")
    private String companyName;

    @Column(name="current_price")
    private BigDecimal currentPrice;

    @Column(name="currency")
    private String currency;

    @Column(name="open")
    private BigDecimal open;

    @Column(name="previous_close")
    private BigDecimal previousClose;

    @Column(name="last_updated")
    private Instant lastUpdated;
}
