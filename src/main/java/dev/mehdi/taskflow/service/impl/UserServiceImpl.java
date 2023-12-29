package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.Role;
import dev.mehdi.taskflow.domain.model.User;
import dev.mehdi.taskflow.dto.user.UserRequestDto;
import dev.mehdi.taskflow.exception.ResourceNotFoundException;
import dev.mehdi.taskflow.repository.UserRepository;
import dev.mehdi.taskflow.service.RoleService;
import dev.mehdi.taskflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User create(UserRequestDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        userDto.getRolesIds().forEach(roleId -> {
            Role role = roleService.getById(roleId)
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
            user.addRole(role);
        });
        return create(user);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
