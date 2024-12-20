package inventario.back.Controller;

import inventario.back.Dto.SignupRequest;
import inventario.back.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {

    private final AuthService authService;

    @Autowired
    public SignupController(AuthService authService) {
        this.authService = authService;
    }
@PostMapping
    public ResponseEntity<String> signupUser(@RequestBody SignupRequest signupRequest) {
        boolean isUserCreated = authService.createdUser(signupRequest);

        if (isUserCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede crear el usuario");
        }
    }

}
