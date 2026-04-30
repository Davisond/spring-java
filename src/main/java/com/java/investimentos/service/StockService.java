package com.java.investimentos.service;

import com.java.investimentos.controller.dto.CreateStockDto;
import com.java.investimentos.entity.Stock;
import com.java.investimentos.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(CreateStockDto createStockDto) {

        //DTO -> entity

        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );
        stockRepository.save(stock);


    }
}
