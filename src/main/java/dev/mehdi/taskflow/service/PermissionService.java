package dev.mehdi.taskflow.service;

import dev.mehdi.taskflow.domain.model.Permission;

import java.util.Optional;

public interface PermissionService {
    Permission save(Permission permission);

    Optional<Permission> getByName(String name);
}
