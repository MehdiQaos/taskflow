package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.Permission;
import dev.mehdi.taskflow.repository.PermissionRepository;
import dev.mehdi.taskflow.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Optional<Permission> getByName(String name) {
        return permissionRepository.findByName(name);
    }
}
