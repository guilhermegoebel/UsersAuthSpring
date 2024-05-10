package usersAuth.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        @NotNull
        Long id,

        String username,

        String password,

        String login
)
{
}
