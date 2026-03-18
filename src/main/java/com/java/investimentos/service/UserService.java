package com.java.investimentos.service;

//amarração do que vem do controller e vai para o repository
//metodos

import com.java.investimentos.controller.CreateUserDto;
import com.java.investimentos.entity.User;
import com.java.investimentos.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {


    //injeção de dependencia (precisa de outra classe dentro de uma classe sem insstanciar na mao) -> preciso usar a interface de repository dentro de service
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    //--------CREATE USER---------
    public UUID createUser(CreateUserDto createUserDto){
        //Dto -> entity
        var entity = new User(null, createUserDto.username(), createUserDto.email(), createUserDto.password(), Instant.now(), null);
        var userSaved =  userRepository.save(entity);
        return userSaved.getUserId();
    }

    //-------FIND BY ID-----------
    public Optional<User> getUserById(String userId){
        return userRepository.findById(UUID.fromString(userId));
    }

    //------GET USERS
    public List<User> listUsers() {
       return userRepository.findAll();
    }

    //-----DELETE BY ID
    public void deleteById(String userId) {
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }


}
