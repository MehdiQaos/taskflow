package dev.mehdi.taskflow.seeder;

import dev.mehdi.taskflow.domain.model.Permission;
import dev.mehdi.taskflow.domain.model.Role;
import dev.mehdi.taskflow.domain.model.enums.UserRole;
import dev.mehdi.taskflow.service.PermissionService;
import dev.mehdi.taskflow.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(3)
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {
    private final RoleService roleService;
    private final PermissionService permissionService;

    @Override
    public void run(String... args) {
        Role admin = Role.builder().name(UserRole.ADMIN).build();
        Permission CREATE = permissionService.getByName("CREATE").orElseThrow();
        Permission READ = permissionService.getByName("READ").orElseThrow();
        Permission UPDATE = permissionService.getByName("UPDATE").orElseThrow();
        Permission DELETE = permissionService.getByName("DELETE").orElseThrow();
        admin.addPermission(CREATE);
        admin.addPermission(READ);
        admin.addPermission(UPDATE);
        admin.addPermission(DELETE);

        Role user = Role.builder().name(UserRole.USER).build();
        user.addPermission(READ);

        roleService.createIfNotExist(admin);
        roleService.createIfNotExist(user);
//        Arrays.stream(UserRole.values()).forEach(role -> {
//            roleService.createIfNotExist(Role.builder().name(role).build());
//        });
    }
}
