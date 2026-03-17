package com.java.investimentos.service;

//amarração do que vem do controller e vai para o repository
//metodos

import com.java.investimentos.controller.CreateUserDto;
import com.java.investimentos.entity.User;
import com.java.investimentos.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {


    //injeção de dependencia (precisa de outra classe dentro de uma classe sem insstanciar na mao) -> preciso usar a interface de repository dentro de service
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto){

        //Dto -> entity
        var entity = new User(null, createUserDto.username(), createUserDto.email(), createUserDto.password(), Instant.now(), null);


        var userSaved =  userRepository.save(entity);


        return userSaved.getUserId();




    }
}
