package com.java.investimentos.controller;

//Pra nao expor tudo no banco p API, cria-se esse meio termo, aqui eu só vou passar o que importa pra criar o usuario
//entao o dto vai pedir só os campos que a gente precisa, username, email e password, agora só usar no controller
public record CreateUserDto(String username, String email, String password) {




}
