package com.java.investimentos.controller;
import com.java.investimentos.controller.dto.*;
import com.java.investimentos.entity.User;
import com.java.investimentos.service.StockService;
import com.java.investimentos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/v1/stocks")
public class StockController {

    private StockService stockService;


    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping //mapeia requisicao
    public ResponseEntity<Void> createStock(@RequestBody CreateStockDto createStockDto){
        stockService.createStock(createStockDto);
        return ResponseEntity.ok().build();
    }














}
