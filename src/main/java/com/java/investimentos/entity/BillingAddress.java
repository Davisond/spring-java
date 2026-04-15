package com.java.investimentos.entity;

//endereco de cobranca
//uma account pode ter somente um endereço de cobrança

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_billingAddress")
public class BillingAddress {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "account_id")//FK(chave estrangeira) da tabela account
    private Account account; //um endereco de cobranca esta relacionado a uma conta

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Integer number;



    public BillingAddress() {
    }

    public BillingAddress(UUID id, String street, Integer number) {
        this.id = id;
        this.street = street;
        this.number = number;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
