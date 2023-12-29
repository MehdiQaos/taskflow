package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.domain.model.Role;
import dev.mehdi.taskflow.domain.model.enums.UserRole;
import dev.mehdi.taskflow.exception.ResourceExistException;
import dev.mehdi.taskflow.repository.RoleRepository;
import dev.mehdi.taskflow.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Optional<Role> getById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> getByName(String name) {
        UserRole role;
        try {
            role = UserRole.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
        return getByName(role);
    }

    @Override
    public Optional<Role> getByName(UserRole role) {
        return roleRepository.findByName(role);
    }

    @Override
    public Role create(Role role) {
        getByName(role.getName()).ifPresent(r -> {
            throw new ResourceExistException("Role already exists");
        });
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> createIfNotExist(Role role) {
        if (roleRepository.findByName(role.getName()).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(roleRepository.save(role));
    }
}