package usersAuth.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import usersAuth.dto.CreateUserDTO;
import usersAuth.dto.GetUserListDTO;
import usersAuth.dto.ReturnUserDTO;
import usersAuth.dto.UpdateUserDTO;
import usersAuth.entity.User;
import usersAuth.repository.UserRepository;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ReturnUserDTO> createUser(@RequestBody @Valid CreateUserDTO dto, UriComponentsBuilder uriBuilder){
        var user = new User(dto);
        user.setPassword(dto.password());
        repository.save(user);

        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new ReturnUserDTO(user));
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<GetUserListDTO>> getAll(@PageableDefault(size = 2, sort = {"username"}) Pageable pageable){
        var page  = repository.findAll(pageable).map(GetUserListDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<ReturnUserDTO> getUserById(@PathVariable Long id) {
        var user = repository.getReferenceById(id);

        return ResponseEntity.ok(new ReturnUserDTO(user));
    }

    @PutMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<ReturnUserDTO> updateUser(@RequestBody @Valid UpdateUserDTO dto){
        var user = repository.getReferenceById(dto.id());
        user.updateUser(dto);

        return ResponseEntity.ok(new ReturnUserDTO(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }



}
