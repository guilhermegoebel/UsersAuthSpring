package usersAuth.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usersAuth.dto.AuthDTO;
import usersAuth.dto.TokenDTO;
import usersAuth.entity.User;
import usersAuth.infra.security.JWTService;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthDTO dto){
        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = authenticationManager.authenticate(token);

        var jwt = jwtService.getToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(jwt));
    }
}
