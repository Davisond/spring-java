package com.java.investimentos.service;


import com.java.investimentos.controller.dto.AccountResponseDto;
import com.java.investimentos.controller.dto.AccountStockResponseDto;
import com.java.investimentos.controller.dto.AssociateAccountStockDto;
import com.java.investimentos.controller.dto.CreateAccountDto;
import com.java.investimentos.entity.AccountStock;
import com.java.investimentos.entity.AccountStockId;
import com.java.investimentos.repository.AccountRepository;
import com.java.investimentos.repository.AccountStockRepository;
import com.java.investimentos.repository.StockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.UUID;

@Service
public class AccountService {


    private AccountRepository accountRepository;
    private StockRepository stockRepository;
    private AccountStockRepository accountStockRepository;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository, AccountStockRepository accountStockRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.accountStockRepository = accountStockRepository;
    }

    public void associateStock(String accountId, AssociateAccountStockDto associateAccountStockDto) {

        var account = accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));//verifica, se n existir joga exception
        var stock = stockRepository.findById(associateAccountStockDto.stockId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));//verifica, se n existir joga exception

        //DTO -> Entity

        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(id, stock, associateAccountStockDto.quantity(), account);
        accountStockRepository.save(entity);


    }

    public List<AccountStockResponseDto> listStocks(String accountId) {
        var account = accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));//verifica, se n existir joga exception

        return account.getAccountStocks().stream()
                .map(accountStock -> new AccountStockResponseDto(accountStock.
                        getStock()
                        .getStockId(),accountStock
                        .getQuantity(),0.0))
                        .toList();
        
    };

    //





















}
