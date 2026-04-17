package com.java.investimentos.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts ")
public class Account {

    @Id
    @Column(name = "account_Id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @ManyToOne //varias(many) contas(accounts) podem ter somente um(one) usuario(useer)
    @JoinColumn(name = "user_id")//coloca a fk do user (chave estrangeira) dentro da tabela de acounts
    private User user; //uma conta vai ter exatamente um usuario

    @OneToOne(mappedBy = "account")//mapped by é literal, indica como tá sendo indicado no BillingAdress
    @PrimaryKeyJoinColumn//indicativo de que a primary key de BillingAdress vem de cá
    private BillingAddress billingAdress;


    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "account") //uma conta pode ter quantas ações financeeiras quiser
    private List<AccountStock> accountStocks;



    public Account() {
    }

    public Account(UUID accountId, String description) {
        this.accountId = accountId;
        this.description = description;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
