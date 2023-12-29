package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.User;
import dev.mehdi.taskflow.dto.user.UserRequestDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAll();

    public Optional<User> getById(Long id);

    public Optional<User> getByEmail(String email);

    public User create(UserRequestDto userDto);

    User create(User user);

    public void delete(User user);
}
