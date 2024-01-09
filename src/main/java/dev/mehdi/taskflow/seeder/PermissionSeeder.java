package dev.mehdi.taskflow.seeder;

import dev.mehdi.taskflow.domain.model.Permission;
import dev.mehdi.taskflow.service.PermissionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(2)
public class PermissionSeeder implements CommandLineRunner {
    private final PermissionService permissionService;

    private final List<Permission> PERMISSIONS = List.of(
        Permission.builder().name("CREATE").description("create permission").build(),
        Permission.builder().name("READ").description("read permission").build(),
        Permission.builder().name("UPDATE").description("update permission").build(),
        Permission.builder().name("DELETE").description("delete permission").build()
    );

    public PermissionSeeder(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public void run(String... args) {
        PERMISSIONS.forEach(this::createIfNotExist);
    }

    private void createIfNotExist(Permission permission) {
        permissionService.getByName(permission.getName())
                .ifPresentOrElse(p -> {}, () -> permissionService.save(permission));
    }
}
