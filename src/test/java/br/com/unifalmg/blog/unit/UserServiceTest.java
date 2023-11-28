package br.com.unifalmg.blog.unit;

import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.exception.UserNotFoundException;
import br.com.unifalmg.blog.repository.UserRepository;
import br.com.unifalmg.blog.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Test
    @DisplayName("#findById > When the id is null > Throw an exception")
    void findByIdWhenTheIdIsNullThrowAnException() {
        assertThrows(IllegalArgumentException.class, () ->
                service.findById(null));
    }

    @Test
    @DisplayName("#findById > When the id is not null > When a user is found > Return the user")
    void findByIdWhenTheIdIsNotNullWhenAUserIsFoundReturnTheUser() {
        when(repository.findById(1)).thenReturn(Optional.of(User.builder()
                .id(1)
                .name("Fellipe")
                .username("felliperey")
                .build()));
        User response = service.findById(1);
        assertAll(
                () -> assertEquals(1, response.getId()),
                () -> assertEquals("Fellipe", response.getName()),
                () -> assertEquals("felliperey", response.getUsername())
        );
    }

    @Test
    @DisplayName("#findById > When the id is not null > When no user is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullWhenNoUserIsFoundThrowAnException() {
        when(repository.findById(2)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () ->
                service.findById(2));
    }

    @Test
    @DisplayName("#getAllUsers > When there are users > Return the list of users")
    void getAllUsersWhenThereAreUsersReturnListOfUsers() {

        when(repository.findAll()).thenReturn(Arrays.asList(
                User.builder().id(1).name("User1").username("user1").build(),
                User.builder().id(2).name("User2").username("user2").build()
        ));


        List<User> result = service.getAllUsers();


        assertEquals(2, result.size());
        assertEquals("User1", result.get(0).getName());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("User2", result.get(1).getName());
        assertEquals("user2", result.get(1).getUsername());
    }

    @Test
    @DisplayName("#getAllUsers > When there are no users > Return an empty list")
    void getAllUsersWhenThereAreNoUsersReturnEmptyList() {

        when(repository.findAll()).thenReturn(Collections.emptyList());


        List<User> result = service.getAllUsers();


        assertEquals(0, result.size());
    }
}