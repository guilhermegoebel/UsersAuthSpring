package usersAuth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserDTO(

        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotBlank
        String login

)
{

}
