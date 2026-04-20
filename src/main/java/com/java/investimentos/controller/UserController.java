package com.java.investimentos.controller;

import com.java.investimentos.controller.dto.CreateAccountDto;
import com.java.investimentos.controller.dto.CreateUserDto;
import com.java.investimentos.controller.dto.UpdateUserDto;
import com.java.investimentos.entity.User;
import com.java.investimentos.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

//anotation p spring criar a classe no formato esperado, a controller vai ser a porta de entrada do projeto,
// ela chama a service que vai chamar o banco de dados(pelo repository), retorna tudo p usuario que chamar a API
@RestController //spring entender que é controller, classe de endpoint
@RequestMapping("/v1/users")//define o caminho da url p chegar na api
public class UserController {

    //injeção de dependencia (explicado no userService
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping //mapeia requisicao
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto){
        var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}") //mapeia requisicao
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        var user = userService.getUserById(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
    var users = userService.listUsers();

    return ResponseEntity.ok(users);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUseById(@PathVariable("userId") String userId, @RequestBody UpdateUserDto updateUserDto) {
    userService.updateUserById(userId, updateUserDto);
    return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId){
    userService.deleteById(userId);

    return ResponseEntity.noContent().build();
    }

    //criação da account
    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId, @RequestBody CreateAccountDto createAccountDto){
        userService.createAccount(userId);

        return ResponseEntity.ok().build();
    }


}




