package dev.mehdi.taskflow.seeder;

import dev.mehdi.taskflow.domain.model.Role;
import dev.mehdi.taskflow.domain.model.enums.UserRole;
import dev.mehdi.taskflow.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(1)
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {
    private final RoleService roleService;
    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(UserRole.values()).forEach(role -> {
            roleService.createIfNotExist(Role.builder().name(role).build());
        });
    }
}
