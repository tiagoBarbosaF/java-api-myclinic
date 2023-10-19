package med.myclinic.api.controller;

import jakarta.validation.Valid;
import med.myclinic.api.domain.user.AuthenticationData;
import med.myclinic.api.domain.user.User;
import med.myclinic.api.infra.security.TokenJWTData;
import med.myclinic.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenJWTData> login(@RequestBody @Valid AuthenticationData data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.tokenGenerate((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }
}
