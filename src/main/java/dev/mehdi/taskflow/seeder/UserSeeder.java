package dev.mehdi.taskflow.seeder;

import dev.mehdi.taskflow.dto.user.UserRequestDto;
import dev.mehdi.taskflow.service.AuthService;
import dev.mehdi.taskflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(4)
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {
    private final UserService userService;
    private final AuthService authService;

    private final List<UserRequestDto> users = List.of(
        new UserRequestDto("Mehdi", "Qaos", "m@q.com", "123456", List.of(1L)),
        new UserRequestDto("Mohammed", "Qaos", "m.q@q.com", "123456", List.of(1L)),
        new UserRequestDto("Yip", "Man", "y@m.com", "123456", List.of(1L, 2L))
    );

    @Override
    public void run(String... args) throws Exception {
        users.forEach(this::createIfNotExist);
    }

    private void createIfNotExist(UserRequestDto user) {
        if (userService.getByEmail(user.getEmail()).isEmpty()) {
            authService.register(user);
        }
    }
}
