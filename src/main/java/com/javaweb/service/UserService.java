package com.javaweb.service;

import com.javaweb.domain.User;
import com.javaweb.domain.dto.RegisterDTO;
import com.javaweb.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String handleHello() {
        return "Hello from Service";
    }

    public User handleSaveUser(User user) {
        User newUser = this.userRepository.save(user);
        System.out.println(newUser);
        return newUser;
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public List<User> getAllUsersByEmail(String email) {
        return this.userRepository.findOneByEmail(email);
    }

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public void handleDeleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();

        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());

        return user;
    }

    public boolean checkEmailExists(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public long handleCountUser() {
        return this.userRepository.count();
    }
}
