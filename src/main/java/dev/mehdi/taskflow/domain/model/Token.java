package dev.mehdi.taskflow.domain.model;

import dev.mehdi.taskflow.domain.model.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TokenType type;
    private String token;
    private Boolean isValid;

    @ManyToOne
    private User user;
}
