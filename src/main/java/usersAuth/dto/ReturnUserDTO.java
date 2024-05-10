package usersAuth.dto;

import usersAuth.entity.User;

public record ReturnUserDTO(Long id, String username, String password, String login) {
    public ReturnUserDTO(User user){
        this(user.getId(), user.getUsernameEntity(), user.getPassword(), user.getLogin());
    }
}
