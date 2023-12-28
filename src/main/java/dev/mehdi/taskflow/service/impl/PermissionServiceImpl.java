package dev.mehdi.taskflow.service.impl;

import dev.mehdi.taskflow.repository.PermissionRepository;
import dev.mehdi.taskflow.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
}
