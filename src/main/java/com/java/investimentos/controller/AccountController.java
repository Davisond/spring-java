package com.java.investimentos.controller;



import com.java.investimentos.controller.dto.*;
import com.java.investimentos.entity.User;
import com.java.investimentos.service.AccountService;
import com.java.investimentos.service.StockService;
import com.java.investimentos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/v1/accounts")
public class AccountController {


    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable("accountId") String accountId, @RequestBody AssociateAccountStockDto associateAccountStockDto){
        accountService.associateStock(accountId, associateAccountStockDto);

        return ResponseEntity.ok().build();
    }






}
