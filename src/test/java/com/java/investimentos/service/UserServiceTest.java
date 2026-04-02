//junit5 + mockito
// arrange -> organiza o que precisa p teste
// act -> chama o trecho a ser testado
// assert -> efetuar verificações

package com.java.investimentos.service;

import com.java.investimentos.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository; //mockar dependencias exteriores

    @InjectMocks
    private UserService userService; //injetar dependencias mockadas

    @Nested //notation p junit identificar que é uma subclasse
    class createUser {

        @Test
        void deveCriarUmUsuario() {
            var user = new User(
                UUID.randomUUID(),
                "user",
                "user@gmail.com",
                "senha",
                Instant.now(),
                null
            );

            //arrang
            doReturn(user).when(userRepository).save(any());
            var input = new CreateUserDto("davison", "davison@gmail.com", "123");
            //act
            var output = userService.createUser(input);
            //assert
            assertNotNull(output);  





        }



    }
}