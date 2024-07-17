package com.forhub.api.service;

import com.forhub.api.dto.user.CreateUserDTO;
import com.forhub.api.dto.user.UserDTO;
import com.forhub.api.model.User;
import com.forhub.api.repository.UserRepository;
import com.forhub.api.infra.errores.DuplicateEntityException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO createUser(CreateUserDTO createUserDTO) {
        if (userRepository.existsByUsername(createUserDTO.username())) {
            throw new DuplicateEntityException("Username already exists");
        }

        User user = new User();
        user.setUsername(createUserDTO.username());
        user.setPassword(passwordEncoder.encode(createUserDTO.password()));
        userRepository.save(user);
        return mapToDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return mapToDTO(user);
    }

    public UserDTO updateUser(Long id, CreateUserDTO updateUserDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setUsername(updateUserDTO.username());
        user.setPassword(passwordEncoder.encode(updateUserDTO.password()));
        userRepository.save(user);
        return mapToDTO(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
    }

    private UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername());
    }
}
