package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.Role;
import dev.mehdi.taskflow.domain.model.enums.UserRole;

import java.util.Optional;

public interface RoleService {
    public Optional<Role> getById(Long id);

    Optional<Role> getByName(String name);

    Optional<Role> getByName(UserRole name);

    Role create(Role role);

    Optional<Role> createIfNotExist(Role role);
}
