package usersAuth.dto;

import usersAuth.entity.User;

public record GetUserListDTO(Long id, String username, String login) {

    public GetUserListDTO(User user){
        this(user.getId(), user.getUsernameEntity(), user.getLogin());
    }
}
