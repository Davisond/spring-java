package com.java.investimentos.service;

//amarração do que vem do controller e vai para o repository
//metodos

import com.java.investimentos.controller.dto.AccountResponseDto;
import com.java.investimentos.controller.dto.CreateAccountDto;
import com.java.investimentos.controller.dto.CreateUserDto;
import com.java.investimentos.controller.dto.UpdateUserDto;
import com.java.investimentos.entity.Account;
import com.java.investimentos.entity.BillingAddress;
import com.java.investimentos.entity.User;
import com.java.investimentos.repository.AccountRepository;
import com.java.investimentos.repository.BillingAddressRepository;
import com.java.investimentos.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
public class UserService {


    //injeção de dependencia (precisa de outra classe dentro de uma classe sem insstanciar na mao) -> preciso usar a interface de repository dentro de service
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
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

    //------UPDATE BY ID
    public void updateUserById(String userId, UpdateUserDto updateUserDto){
        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()){
            var user = userEntity.get();

            if (updateUserDto.username() != null){
                user.setUsername(updateUserDto.username());
            }
            if (updateUserDto.password() != null){
                user.setPassword(updateUserDto.password());
            }

            userRepository.save(user);
        }
    }

    //-----DELETE BY ID
    public void deleteById(String userId) {
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }


    public void createAccount(String userId, CreateAccountDto createAccountDto) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao existe"));//Se passar user n existente -> not found

        //dto -> entity
        var account = new Account();

        account.setUser(user);
        account.setDescription(createAccountDto.description());
        account.setAccountStocks(new ArrayList<>());

        var billingAddress = new BillingAddress(
                account.getAccountId(),
                account,
                createAccountDto.street(),
                createAccountDto.number()
        );

        account.setBillingAddress(billingAddress);
        accountRepository.save(account);

    }




    public List<AccountResponseDto> listAccounts(String userId) {
        var user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));//Se passar user n existente -> not found

        return user.getAccounts()//Lista de users
                .stream()
                .map(ac ->//mapear -> recebe cada account e instancia um novo Dto coletando com toList
                        new AccountResponseDto(ac.getAccountId().toString(),ac.getDescription()))
                .toList();
    }
}
