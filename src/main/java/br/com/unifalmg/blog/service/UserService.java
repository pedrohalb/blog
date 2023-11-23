package br.com.unifalmg.blog.service;

import br.com.unifalmg.blog.entity.User;
import br.com.unifalmg.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

}
