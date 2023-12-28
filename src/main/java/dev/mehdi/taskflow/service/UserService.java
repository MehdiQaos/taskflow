package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAll();

    public Optional<User> getById(Long id);

    public User save(User user);

    public void delete(User user);
}
