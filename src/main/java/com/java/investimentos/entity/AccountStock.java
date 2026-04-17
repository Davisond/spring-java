package com.java.investimentos.entity;

//classe mediária de relação entre account e stock

import jakarta.persistence.*;

@Entity
@Table(name = "tb_account_stocks")
public class AccountStock {

    @EmbeddedId//mostra pro hibernet que o id utilizado é a classe AccountStockId
    private AccountStockId id;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;//relacionamento accountStock -> account


    @ManyToOne
    @MapsId("stockId")
    @JoinColumn(name = "stock_id")
    private Stock stock;//relacionamento

    @Column(name = "quantity")
    private Integer quantity;

    public AccountStock() {
    }

    public AccountStock(AccountStockId id, Stock stock, Integer quantity, Account account) {
        this.id = id;
        this.stock = stock;
        this.quantity = quantity;
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountStockId getId() {
        return id;
    }

    public void setId(AccountStockId id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
