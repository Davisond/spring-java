package com.java.investimentos.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.annotation.processing.Generated;
import java.time.Instant;
import java.util.UUID;

@Entity//indica ao spring criar e utilizar a classe como referencia no banco de dados
@Table(name = "tb_users") //indica ao banco qual o nome da tabela

public class User {

    @Id //indica o campo como primary key na tabela
    @GeneratedValue(strategy = GenerationType.UUID)//Gera automaticamente o Id
    private UUID UserId;

    @Column(name = "username")//notation que permite colocar o nome que eu quser da coluna no banco
    private String username;

    @Column(name = "email")//pq o msm nome? se eu refatorar, n impacta no banco
    private String email;


    @Column(name = "password")
    private String password;

    @CreationTimestamp //permite o hibernate gerenciar
    private Instant creationTimestamp; //campo de auditoria p saber qnd foi criado

    @UpdateTimestamp
    private Instant updatedTimestamp;


    public User() {
    }

    //construtor com tds os campos criado com alt + insert
    public User(UUID userId, String username, String email, String password, Instant creationTimestamp, Instant updatedTimestamp) {
        UserId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationTimestamp = creationTimestamp;
        this.updatedTimestamp = updatedTimestamp;
    }

    public UUID getUserId() {
        return UserId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public Instant getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public void setUpdatedTimestamp(Instant updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }
}
